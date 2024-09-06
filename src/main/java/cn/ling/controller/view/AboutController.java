package cn.ling.controller.view;

import cn.ling.annotation.VisitLogger;
import cn.ling.enums.VisitBehavior;
import cn.ling.service.AboutService;
import cn.ling.util.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 关于我页面
 */
@RestController
public class AboutController {
	private final AboutService aboutService;

	public AboutController(AboutService aboutService) {
		this.aboutService = aboutService;
	}

	/**
	 * 获取关于我页面信息
	 *
	 * @return Result
	 */
	@VisitLogger(VisitBehavior.ABOUT)
	@GetMapping("/about")
	public Result about() {
		return Result.success("获取成功", aboutService.getAboutInfo());
	}
}
