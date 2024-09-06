package cn.ling.mapper;

import cn.ling.entity.VisitLog;
import cn.ling.model.dto.VisitLogUuidTimeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 访问日志持久层接口
 */
@Mapper
@Repository
public interface VisitLogMapper {
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
	 * @return int
	 */
	int saveVisitLog(VisitLog log);

	/**
	 * 删除日志
	 * @param id id
	 * @return int
	 */
	int deleteVisitLogById(Long id);

	/**
	 * 查询今日访问量
	 * @return int
	 */
	int countVisitLogByToday();
}
