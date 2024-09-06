package cn.ling.controller.view;

import cn.ling.annotation.VisitLogger;
import cn.ling.enums.VisitBehavior;
import cn.ling.model.vo.BlogInfoVO;
import cn.ling.model.vo.PageResultVO;
import cn.ling.service.BlogService;
import cn.ling.util.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 标签
 */
@RestController
public class TagController {
	private final BlogService blogService;

	public TagController(BlogService blogService) {
		this.blogService = blogService;
	}

	/**
	 * 根据标签name分页查询公开博客列表
	 *
	 * @param tagName 标签name
	 * @param pageNum 页码
	 * @return Result
	 */
	@VisitLogger(VisitBehavior.TAG)
	@GetMapping("/tag")
	public Result tag(@RequestParam String tagName,
	                  @RequestParam(defaultValue = "1") Integer pageNum) {
		PageResultVO<BlogInfoVO> pageResultVO = blogService.getBlogInfoListByTagNameAndIsPublished(tagName, pageNum);
		return Result.success("请求成功", pageResultVO);
	}
}
