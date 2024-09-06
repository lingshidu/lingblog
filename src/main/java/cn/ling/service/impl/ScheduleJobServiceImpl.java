package cn.ling.service.impl;

import cn.ling.entity.ScheduleJob;
import cn.ling.entity.ScheduleJobLog;
import cn.ling.exception.PersistenceException;
import cn.ling.mapper.ScheduleJobLogMapper;
import cn.ling.mapper.ScheduleJobMapper;
import cn.ling.service.ScheduleJobService;
import cn.ling.util.quartz.ScheduleUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 定时任务业务层实现
 */
@Service
public class ScheduleJobServiceImpl implements ScheduleJobService {
	private final Scheduler scheduler;
	private final ScheduleJobMapper schedulerJobMapper;
	private final ScheduleJobLogMapper scheduleJobLogMapper;

	public ScheduleJobServiceImpl(Scheduler scheduler, ScheduleJobMapper schedulerJobMapper, ScheduleJobLogMapper scheduleJobLogMapper) {
		this.scheduler = scheduler;
		this.schedulerJobMapper = schedulerJobMapper;
		this.scheduleJobLogMapper = scheduleJobLogMapper;
	}

	/**
	 * 项目启动时，初始化定时器
	 */
	@PostConstruct
	public void init() {
		List<ScheduleJob> scheduleJobList = getJobList();
		for (ScheduleJob scheduleJob : scheduleJobList) {
			CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
			//如果不存在，则创建
			if (cronTrigger == null) {
				ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
			} else {
				ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
			}
		}
	}

	@Override
	public List<ScheduleJob> getJobList() {
		return schedulerJobMapper.getJobList();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveJob(ScheduleJob scheduleJob) {
		if (schedulerJobMapper.saveJob(scheduleJob) != 1) {
			throw new PersistenceException("添加失败");
		}
		ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateJob(ScheduleJob scheduleJob) {
		if (schedulerJobMapper.updateJob(scheduleJob) != 1) {
			throw new PersistenceException("更新失败");
		}
		ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteJobById(Long jobId) {
		ScheduleUtils.deleteScheduleJob(scheduler, jobId);
		if (schedulerJobMapper.deleteJobById(jobId) != 1) {
			throw new PersistenceException("删除失败");
		}
	}

	@Override
	public void runJobById(Long jobId) {
		ScheduleUtils.run(scheduler, schedulerJobMapper.getJobById(jobId));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateJobStatusById(Long jobId, Boolean status) {
		if (Boolean.TRUE.equals(status)) {
			ScheduleUtils.resumeJob(scheduler, jobId);
		} else {
			ScheduleUtils.pauseJob(scheduler, jobId);
		}
		if (schedulerJobMapper.updateJobStatusById(jobId, status) != 1) {
			throw new PersistenceException("修改失败");
		}
	}

	@Override
	public List<ScheduleJobLog> getJobLogListByDate(String startDate, String endDate) {
		return scheduleJobLogMapper.getJobLogListByDate(startDate, endDate);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveJobLog(ScheduleJobLog jobLog) {
		if (scheduleJobLogMapper.saveJobLog(jobLog) != 1) {
			throw new PersistenceException("日志添加失败");
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void deleteJobLogByLogId(Long logId) {
		if (scheduleJobLogMapper.deleteJobLogByLogId(logId) != 1) {
			throw new PersistenceException("日志删除失败");
		}
	}
}
