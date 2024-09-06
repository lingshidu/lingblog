package cn.ling.task;

import cn.ling.constant.CommonConstants;
import cn.ling.constant.RedisKeyConstants;
import cn.ling.entity.CityVisitor;
import cn.ling.entity.VisitRecord;
import cn.ling.model.dto.VisitLogUuidTimeDTO;
import cn.ling.service.*;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 访客统计相关定时任务
 */
@Component
public class VisitorSyncScheduleTask {
	private final RedisService redisService;
	private final VisitLogService visitLogService;
	private final VisitorService visitorService;
	private final VisitRecordService visitRecordService;
	private final CityVisitorService cityVisitorService;

	public VisitorSyncScheduleTask(RedisService redisService, VisitLogService visitLogService,
								   VisitorService visitorService, VisitRecordService visitRecordService,
								   CityVisitorService cityVisitorService) {
		this.redisService = redisService;
		this.visitLogService = visitLogService;
		this.visitorService = visitorService;
		this.visitRecordService = visitRecordService;
		this.cityVisitorService = cityVisitorService;
	}

	/**
	 * 清空当天Redis访客标识
	 * 记录当天的PV和UV
	 * 更新当天所有访客的PV和最后访问时间
	 * 更新城市新增访客UV数
	 */
	public void syncVisitInfoToDatabase() {
		//清空当天Redis的访客标识Set，以便统计每日UV
		redisService.deleteCacheByKey(RedisKeyConstants.IDENTIFICATION_SET);
		//获取昨天的所有访问日志
		List<VisitLogUuidTimeDTO> yesterdayLogList = visitLogService.getUuidAndCreateTimeByYesterday();
		//用Set去重得到当天所有访客的uuid
		//为避免缓存击穿导致第二天的数据统计不准确，以数据库访问日志为准，而不从Redis中获取这个Set
		//比如在这个定时任务执行期间，产生大量访客的请求，而这些访客的uuid都在任务执行结束后被清空了，没有被第二天的定时任务记录到
		Set<String> uuidSet = new HashSet<>();
		Map<String, Integer> pvMap = new HashMap<>(16);
		Map<String, Date> lastTimeMap = new HashMap<>(16);
		yesterdayLogList.forEach(log -> {
			String uuid = log.getUuid();
			Date createTime = log.getTime();
			//记录当天访客的uuid
			uuidSet.add(uuid);
			//对应uuid的PV++
			pvMap.merge(uuid, 1, Integer::sum);
			//因为sql中order by create_time desc，直接put第一次出现的uuid的createTime，就是最后一次访问时间
			lastTimeMap.putIfAbsent(uuid, createTime);
		});
		int pv = yesterdayLogList.size();
		int uv = uuidSet.size();
		//获取昨天的日期字符串
		String date = new SimpleDateFormat("MM-dd").format(DateUtils.addDays(new Date(), -1));
		//记录当天的PV和UV
		visitRecordService.saveVisitRecord(new VisitRecord(pv, uv, date));
		//更新当天所有访客的PV和最后访问时间到数据库
		uuidSet.forEach(uuid -> {
			VisitLogUuidTimeDTO uuidPvTimeDTO = new VisitLogUuidTimeDTO(uuid, lastTimeMap.get(uuid), pvMap.get(uuid));
			visitorService.updatePvAndLastTimeByUuid(uuidPvTimeDTO);
		});
		//查询当天新增访客的ip来源
		List<String> ipSource = visitorService.getNewVisitorIpSourceByYesterday();
		Map<String, Integer> cityVisitorMap = new HashMap<>(16);
		ipSource.forEach(i -> {
			if (i.startsWith("中国")) {
				String[] split = i.split("\\|");
				if (split.length == CommonConstants.FOUR) {
					String city = split[2];
					cityVisitorMap.merge(city, 1, Integer::sum);
				}
			}
		});
		//更新城市新增访客UV数
		cityVisitorMap.forEach((k, v) -> cityVisitorService.saveCityVisitor(new CityVisitor(k, v)));
	}
}
