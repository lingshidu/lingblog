package cn.ling.controller.view;

import cn.ling.annotation.VisitLogger;
import cn.ling.entity.Category;
import cn.ling.enums.VisitBehavior;
import cn.ling.model.vo.BlogInfoVO;
import cn.ling.model.vo.PageResultVO;
import cn.ling.service.BlogService;
import cn.ling.service.CategoryService;
import cn.ling.util.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 分类
 */
@RestController
public class CategoryController {

	private final CategoryService categoryService;

	private final BlogService blogService;

	public CategoryController(CategoryService categoryService, BlogService blogService) {
		this.categoryService = categoryService;
		this.blogService = blogService;
	}


	/**
	 * 根据分类name分页查询公开博客列表
	 *
	 * @param categoryName 分类name
	 * @param pageNum      页码
	 * @return Result
	 */
	@VisitLogger(VisitBehavior.CATEGORY)
	@GetMapping("/category")
	public Result category(@RequestParam String categoryName,
	                       @RequestParam(defaultValue = "1") Integer pageNum) {
		PageResultVO<BlogInfoVO> pageResultVO = blogService.getBlogInfoListByCategoryNameAndIsPublished(categoryName, pageNum);
		return Result.success("请求成功", pageResultVO);
	}

	/**
	 * 获取所有分类名称
	 * @return
	 */
	@GetMapping("/category/list")
	public Result categories() {
		List<Category> categoryList = categoryService.getCategoryList();
		return Result.success("请求成功",categoryList);
	}

}
