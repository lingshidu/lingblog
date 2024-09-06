package cn.ling.mapper;

import cn.ling.entity.ScheduleJobLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 定时任务日志持久层接口
 */
@Mapper
@Repository
public interface ScheduleJobLogMapper {
	/**
	 * 分页查询定时任务日志列表
	 * @param startDate startDate
	 * @param endDate endDate
	 * @return List<ScheduleJobLog>
	 */
	List<ScheduleJobLog> getJobLogListByDate(String startDate, String endDate);

	/**
	 * 保存任务日志
	 * @param jobLog jobLog
	 * @return int
	 */
	int saveJobLog(ScheduleJobLog jobLog);

	/**
	 * 按id删除任务日志
	 * @param logId logId
	 * @return int
	 */
	int deleteJobLogByLogId(Long logId);
}
