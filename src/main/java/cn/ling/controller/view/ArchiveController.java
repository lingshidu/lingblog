package cn.ling.controller.view;

import cn.ling.annotation.VisitLogger;
import cn.ling.enums.VisitBehavior;
import cn.ling.service.BlogService;
import cn.ling.util.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 归档页面
 */
@RestController
public class ArchiveController {
	private final BlogService blogService;

	public ArchiveController(BlogService blogService) {
		this.blogService = blogService;
	}

	/**
	 * 按年月分组归档公开博客 统计公开博客总数
	 *
	 * @return Result
	 */
	@VisitLogger(VisitBehavior.ARCHIVE)
	@GetMapping("/archives")
	public Result archives() {
		Map<String, Object> archiveBlogMap = blogService.getArchiveBlogAndCountByIsPublished();
		return Result.success("请求成功", archiveBlogMap);
	}
}
