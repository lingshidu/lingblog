package cn.ling.mapper;

import cn.ling.entity.ScheduleJob;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 定时任务持久层接口
 */
@Mapper
@Repository
public interface ScheduleJobMapper {
	/**
	 * List<ScheduleJob>
	 * @return List<ScheduleJob>
	 */
	List<ScheduleJob> getJobList();

	/**
	 * 获取调度信息
	 * @param jobId jobId
	 * @return ScheduleJob
	 */
	ScheduleJob getJobById(Long jobId);

	/**
	 * scheduleJob
	 * @param scheduleJob scheduleJob
	 * @return int
	 */
	int saveJob(ScheduleJob scheduleJob);

	/**
	 * scheduleJob
	 * @param scheduleJob scheduleJob
	 * @return int
	 */
	int updateJob(ScheduleJob scheduleJob);

	/**
	 * 删除定时任务
	 * @param jobId 任务id
	 * @return int
	 */
	int deleteJobById(Long jobId);

	/**
	 * 更新任务状态：暂停或恢复
	 * @param jobId 任务id
	 * @param status 状态
	 * @return int
	 */
	int updateJobStatusById(Long jobId, Boolean status);
}
