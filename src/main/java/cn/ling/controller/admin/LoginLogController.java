package cn.ling.controller.admin;

import cn.ling.common.CommonService;
import cn.ling.entity.LoginLog;
import cn.ling.model.temp.PageDTO;
import cn.ling.service.LoginLogService;
import cn.ling.util.common.Result;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import org.springframework.web.bind.annotation.*;

/**
 * 登录日志后台管理
 */
@RestController
@RequestMapping("/admin")
public class LoginLogController {
	private final LoginLogService loginLogService;
	private final CommonService commonService;

	public LoginLogController(LoginLogService loginLogService, CommonService commonService) {
		this.loginLogService = loginLogService;
		this.commonService = commonService;
	}

	/**
	 * 分页查询登录日志列表
	 *
	 * @param date     按操作时间查询
	 * @param pageNum  页码
	 * @param pageSize 每页个数
	 * @return Result
	 */
	@GetMapping("/loginLogs")
	public Result loginLogs(@RequestParam(defaultValue = "") String[] date,
	                        @RequestParam(defaultValue = "1") Integer pageNum,
	                        @RequestParam(defaultValue = "10") Integer pageSize) {
		PageDTO pageDTO = commonService.pageBefore(date);
		PageMethod.startPage(pageNum, pageSize, pageDTO.getOrderBy());
		PageInfo<LoginLog> pageInfo = new PageInfo<>(loginLogService.getLoginLogListByDate(pageDTO.getStartDate(), pageDTO.getEndDate()));
		return Result.success("请求成功", pageInfo);
	}

	/**
	 * 按id删除登录日志
	 *
	 * @param id 日志id
	 * @return Result
	 */
	@DeleteMapping("/loginLog")
	public Result delete(@RequestParam Long id) {
		loginLogService.deleteLoginLogById(id);
		return Result.success("删除成功");
	}
}
