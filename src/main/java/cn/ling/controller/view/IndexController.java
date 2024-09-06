package cn.ling.controller.view;

import cn.ling.entity.Category;
import cn.ling.entity.Tag;
import cn.ling.model.vo.NewBlogVO;
import cn.ling.model.vo.RandomBlogVO;
import cn.ling.service.BlogService;
import cn.ling.service.CategoryService;
import cn.ling.service.SiteSettingService;
import cn.ling.service.TagService;
import cn.ling.util.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 站点相关
 */

@RestController
public class IndexController {
	private final SiteSettingService siteSettingService;
	private final BlogService blogService;
	private final CategoryService categoryService;
	private final TagService tagService;

	public IndexController(SiteSettingService siteSettingService, BlogService blogService, CategoryService categoryService, TagService tagService) {
		this.siteSettingService = siteSettingService;
		this.blogService = blogService;
		this.categoryService = categoryService;
		this.tagService = tagService;
	}

	/**
	 * 获取站点配置信息、最新推荐博客、分类列表、标签云、随机博客
	 *
	 * @return Result
	 */
	@GetMapping("/site")
	public Result site() {
		Map<String, Object> map = siteSettingService.getSiteInfo();
		List<NewBlogVO> newBlogVOList = blogService.getNewBlogListByIsPublished();
		List<Category> categoryList = categoryService.getCategoryNameList();
		List<Tag> tagList = tagService.getTagListNotId();
		List<RandomBlogVO> randomBlogVOList = blogService.getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend();
		map.put("newBlogList", newBlogVOList);
		map.put("categoryList", categoryList);
		map.put("tagList", tagList);
		map.put("randomBlogList", randomBlogVOList);
		return Result.success("请求成功", map);
	}
}
