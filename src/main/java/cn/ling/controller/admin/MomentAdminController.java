package cn.ling.controller.admin;

import cn.ling.annotation.OperationLogger;
import cn.ling.entity.Moment;
import cn.ling.service.MomentService;
import cn.ling.util.common.Result;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 博客动态后台管理
 */
@RestController
@RequestMapping("/admin")
public class MomentAdminController {
	private final MomentService momentService;

	public MomentAdminController(MomentService momentService) {
		this.momentService = momentService;
	}

	/**
	 * 分页查询动态列表
	 *
	 * @param pageNum  页码
	 * @param pageSize 每页条数
	 * @return Result
	 */
	@GetMapping("/moments")
	public Result moments(@RequestParam(defaultValue = "1") Integer pageNum,
	                      @RequestParam(defaultValue = "10") Integer pageSize) {
		String orderBy = "create_time desc";
		PageMethod.startPage(pageNum, pageSize, orderBy);
		PageInfo<Moment> pageInfo = new PageInfo<>(momentService.getMomentList());
		return Result.success("请求成功", pageInfo);
	}

	/**
	 * 更新动态公开状态
	 *
	 * @param id        动态id
	 * @param published 是否公开
	 * @return Result
	 */
	@OperationLogger("更新动态公开状态")
	@PutMapping("/moment/published")
	public Result updatePublished(@RequestParam Long id, @RequestParam Boolean published) {
		momentService.updateMomentPublishedById(id, published);
		return Result.success("操作成功");
	}

	/**
	 * 根据id查询动态
	 *
	 * @param id 动态id
	 * @return Result
	 */
	@GetMapping("/moment")
	public Result moment(@RequestParam Long id) {
		return Result.success("获取成功", momentService.getMomentById(id));
	}

	/**
	 * 删除动态
	 *
	 * @param id 动态id
	 * @return Result
	 */
	@OperationLogger("删除动态")
	@DeleteMapping("/moment")
	public Result deleteMoment(@RequestParam Long id) {
		momentService.deleteMomentById(id);
		return Result.success("删除成功");
	}

	/**
	 * 发布动态
	 *
	 * @param moment 动态实体
	 * @return Result
	 */
	@OperationLogger("发布动态")
	@PostMapping("/moment")
	public Result saveMoment(@RequestBody Moment moment) {
		if (moment.getCreateTime() == null) {
			moment.setCreateTime(new Date());
		}
		momentService.saveMoment(moment);
		return Result.success("添加成功");
	}

	/**
	 * 更新动态
	 *
	 * @param moment 动态实体
	 * @return Result
	 */
	@OperationLogger("更新动态")
	@PutMapping("/moment")
	public Result updateMoment(@RequestBody Moment moment) {
		if (moment.getCreateTime() == null) {
			moment.setCreateTime(new Date());
		}
		momentService.updateMoment(moment);
		return Result.success("修改成功");
	}
}
