package cn.ling.controller.admin;

import cn.ling.common.CommonService;
import cn.ling.entity.ExceptionLog;
import cn.ling.model.temp.PageDTO;
import cn.ling.service.ExceptionLogService;
import cn.ling.util.common.Result;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import org.springframework.web.bind.annotation.*;

/**
 * 异常日志后台管理
 */
@RestController
@RequestMapping("/admin")
public class ExceptionLogController {
	private final ExceptionLogService exceptionLogService;
	private final CommonService commonService;

	public ExceptionLogController(ExceptionLogService exceptionLogService, CommonService commonService) {
		this.exceptionLogService = exceptionLogService;
		this.commonService = commonService;
	}

	/**
	 * 分页查询异常日志列表
	 * @param date     按操作时间查询
	 * @param pageNum  页码
	 * @param pageSize 每页个数
	 * @return Result
	 */
	@GetMapping("/exceptionLogs")
	public Result exceptionLogs(@RequestParam(defaultValue = "") String[] date,
	                            @RequestParam(defaultValue = "1") Integer pageNum,
	                            @RequestParam(defaultValue = "10") Integer pageSize) {
		PageDTO pageDTO = commonService.pageBefore(date);
		PageMethod.startPage(pageNum, pageSize, pageDTO.getOrderBy());
		PageInfo<ExceptionLog> pageInfo = new PageInfo<>(exceptionLogService.getExceptionLogListByDate(pageDTO.getStartDate(), pageDTO.getEndDate()));
		return Result.success("请求成功", pageInfo);
	}

	/**
	 * 按id删除异常日志
	 * @param id 日志id
	 * @return Result
	 */
	@DeleteMapping("/exceptionLog")
	public Result delete(@RequestParam Long id) {
		exceptionLogService.deleteExceptionLogById(id);
		return Result.success("删除成功");
	}
}
