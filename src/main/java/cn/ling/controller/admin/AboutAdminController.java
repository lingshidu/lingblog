package cn.ling.controller.admin;

import cn.ling.annotation.OperationLogger;
import cn.ling.service.AboutService;
import cn.ling.util.common.Result;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 关于我页面后台管理
 */
@RestController
@RequestMapping("/admin")
public class AboutAdminController {


	private final AboutService aboutService;

	public AboutAdminController(AboutService aboutService) {
		this.aboutService = aboutService;
	}

	/**
	 * 获取关于我页面配置
	 *
	 * @return Result
	 */
	@GetMapping("/about")
	public Result about() {
		return Result.success("请求成功", aboutService.getAboutSetting());
	}

	/**
	 * 修改关于我页面
	 * @param map map
	 * @return Result
	 */
	@OperationLogger("修改关于我页面")
	@PutMapping("/about")
	public Result updateAbout(@RequestBody Map<String, String> map) {
		aboutService.updateAbout(map);
		return Result.success("修改成功");
	}
}
