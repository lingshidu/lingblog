package cn.ling.controller.admin;

import cn.ling.common.CommonService;
import cn.ling.entity.Visitor;
import cn.ling.model.temp.PageDTO;
import cn.ling.service.VisitorService;
import cn.ling.util.common.Result;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import org.springframework.web.bind.annotation.*;

/**
 * 访客统计
 */
@RestController
@RequestMapping("/admin")
public class VisitorAdminController {
	private final VisitorService visitorService;
	private final CommonService commonService;

	public VisitorAdminController(VisitorService visitorService, CommonService commonService) {
		this.visitorService = visitorService;
		this.commonService = commonService;
	}

	/**
	 * 分页查询访客列表
	 *
	 * @param date     按最后访问时间查询
	 * @param pageNum  页码
	 * @param pageSize 每页个数
	 * @return Result
	 */
	@GetMapping("/visitors")
	public Result visitors(@RequestParam(defaultValue = "") String[] date,
	                       @RequestParam(defaultValue = "1") Integer pageNum,
	                       @RequestParam(defaultValue = "10") Integer pageSize) {
		PageDTO pageDTO = commonService.pageBefore(date);
		pageDTO.setOrderBy("last_time desc");
		PageMethod.startPage(pageNum, pageSize, pageDTO.getOrderBy());
		PageInfo<Visitor> pageInfo = new PageInfo<>(visitorService.getVisitorListByDate(pageDTO.getStartDate(), pageDTO.getEndDate()));
		return Result.success("请求成功", pageInfo);
	}

	/**
	 * 按id删除访客
	 * 按uuid删除Redis缓存
	 *
	 * @param id   访客id
	 * @param uuid 访客uuid
	 * @return Result
	 */
	@DeleteMapping("/visitor")
	public Result delete(@RequestParam Long id, @RequestParam String uuid) {
		visitorService.deleteVisitor(id, uuid);
		return Result.success("删除成功");
	}
}
