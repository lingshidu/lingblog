package cn.ling.controller.admin;

import cn.ling.common.CommonService;
import cn.ling.entity.VisitLog;
import cn.ling.model.temp.PageDTO;
import cn.ling.service.VisitLogService;
import cn.ling.util.common.Result;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 访问日志后台管理
 */
@RestController
@RequestMapping("/admin")
public class VisitLogController {
	private final VisitLogService visitLogService;
	private final CommonService commonService;

	public VisitLogController(VisitLogService visitLogService, CommonService commonService) {
		this.visitLogService = visitLogService;
		this.commonService = commonService;
	}

	/**
	 * 分页查询访问日志列表
	 *
	 * @param uuid     按访客标识码模糊查询
	 * @param date     按访问时间查询
	 * @param pageNum  页码
	 * @param pageSize 每页个数
	 * @return Result
	 */
	@GetMapping("/visitLogs")
	public Result visitLogs(@RequestParam(defaultValue = "") String uuid,
	                        @RequestParam(defaultValue = "") String[] date,
	                        @RequestParam(defaultValue = "1") Integer pageNum,
	                        @RequestParam(defaultValue = "10") Integer pageSize) {
		PageDTO pageDTO = commonService.pageBefore(date);
		PageMethod.startPage(pageNum, pageSize, pageDTO.getOrderBy());
		PageInfo<VisitLog> pageInfo = new PageInfo<>(
				visitLogService.getVisitLogListByUuidAndDate(StringUtils.trim(uuid),
						pageDTO.getStartDate(), pageDTO.getEndDate()));
		return Result.success("请求成功", pageInfo);
	}

	/**
	 * 按id删除访问日志
	 *
	 * @param id 日志id
	 * @return Result
	 */
	@DeleteMapping("/visitLog")
	public Result delete(@RequestParam Long id) {
		visitLogService.deleteVisitLogById(id);
		return Result.success("删除成功");
	}
}
