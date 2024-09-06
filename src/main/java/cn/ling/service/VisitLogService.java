package cn.ling.service;

import cn.ling.entity.VisitLog;
import cn.ling.model.dto.VisitLogUuidTimeDTO;

import java.util.List;

public interface VisitLogService {
	/**
	 * 查询日志
	 * @param uuid uuid
	 * @param startDate startDate
	 * @param endDate endDate
	 * @return List<VisitLog>
	 */
	List<VisitLog> getVisitLogListByUuidAndDate(String uuid, String startDate, String endDate);

	/**
	 * 查询昨天的所有访问日志
	 * @return List<VisitLogUuidTimeDTO>
	 */
	List<VisitLogUuidTimeDTO> getUuidAndCreateTimeByYesterday();


	/**
	 * 添加日志
	 * @param log log
	 */
	void saveVisitLog(VisitLog log);

	/**
	 * 删除日志
	 * @param id id
	 */
	void deleteVisitLogById(Long id);
}
