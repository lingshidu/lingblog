package cn.ling.controller.admin;

import cn.ling.annotation.OperationLogger;
import cn.ling.common.CommonService;
import cn.ling.entity.ScheduleJob;
import cn.ling.entity.ScheduleJobLog;
import cn.ling.model.temp.PageDTO;
import cn.ling.service.ScheduleJobService;
import cn.ling.util.common.Result;
import cn.ling.util.common.ValidatorUtils;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 定时任务动态管理
 */
@RestController
@RequestMapping("/admin")
public class ScheduleJobController {
	private final ScheduleJobService scheduleJobService;
	private final CommonService commonService;

	public ScheduleJobController(ScheduleJobService scheduleJobService, CommonService commonService) {
		this.scheduleJobService = scheduleJobService;
		this.commonService = commonService;
	}

	/**
	 * 分页查询定时任务列表
	 * @param pageNum  页码
	 * @param pageSize 每页条数
	 * @return Result
	 */
	@GetMapping("/jobs")
	public Result jobs(@RequestParam(defaultValue = "1") Integer pageNum,
	                   @RequestParam(defaultValue = "10") Integer pageSize) {
		PageMethod.startPage(pageNum, pageSize);
		PageInfo<ScheduleJob> pageInfo = new PageInfo<>(scheduleJobService.getJobList());
		return Result.success("请求成功", pageInfo);
	}

	/**
	 * 新建定时任务
	 * @param scheduleJob scheduleJob
	 * @return Result
	 */
	@OperationLogger("新建定时任务")
	@PostMapping("/job")
	public Result saveJob(@RequestBody ScheduleJob scheduleJob) {
		scheduleJob.setStatus(false);
		scheduleJob.setCreateTime(new Date());
		ValidatorUtils.validateEntity(scheduleJob);
		scheduleJobService.saveJob(scheduleJob);
		return Result.success("添加成功");
	}

	/**
	 * 修改定时任务
	 * @param scheduleJob scheduleJob
	 * @return Result
	 */
	@OperationLogger("修改定时任务")
	@PutMapping("/job")
	public Result updateJob(@RequestBody ScheduleJob scheduleJob) {
		scheduleJob.setStatus(false);
		ValidatorUtils.validateEntity(scheduleJob);
		scheduleJobService.updateJob(scheduleJob);
		return Result.success("修改成功");
	}

	/**
	 * 删除定时任务
	 *
	 * @param jobId 任务id
	 * @return Result
	 */
	@OperationLogger("删除定时任务")
	@DeleteMapping("/job")
	public Result deleteJob(@RequestParam Long jobId) {
		scheduleJobService.deleteJobById(jobId);
		return Result.success("删除成功");
	}

	/**
	 * 立即执行任务
	 *
	 * @param jobId 任务id
	 * @return Result
	 */
	@OperationLogger("立即执行定时任务")
	@PostMapping("/job/run")
	public Result runJob(@RequestParam Long jobId) {
		scheduleJobService.runJobById(jobId);
		return Result.success("提交执行");
	}

	/**
	 * 更新任务状态：暂停或恢复
	 *
	 * @param jobId  任务id
	 * @param status 状态
	 * @return Result
	 */
	@OperationLogger("更新任务状态")
	@PutMapping("/job/status")
	public Result updateJobStatus(@RequestParam Long jobId, @RequestParam Boolean status) {
		scheduleJobService.updateJobStatusById(jobId, status);
		return Result.success("更新成功");
	}

	/**
	 * 分页查询定时任务日志列表
	 *
	 * @param date     按执行时间查询
	 * @param pageNum  页码
	 * @param pageSize 每页条数
	 * @return Result
	 */
	@GetMapping("/job/logs")
	public Result logs(@RequestParam(defaultValue = "") String[] date,
	                   @RequestParam(defaultValue = "1") Integer pageNum,
	                   @RequestParam(defaultValue = "10") Integer pageSize) {
		PageDTO pageDTO = commonService.pageBefore(date);
		PageMethod.startPage(pageNum, pageSize, pageDTO.getOrderBy());
		PageInfo<ScheduleJobLog> pageInfo = new PageInfo<>(
				scheduleJobService.getJobLogListByDate(pageDTO.getStartDate(), pageDTO.getEndDate())
		);
		return Result.success("请求成功", pageInfo);
	}

	/**
	 * 按id删除任务日志
	 *
	 * @param logId 日志id
	 * @return Result
	 */
	@DeleteMapping("/job/log")
	public Result delete(@RequestParam Long logId) {
		scheduleJobService.deleteJobLogByLogId(logId);
		return Result.success("删除成功");
	}
}
