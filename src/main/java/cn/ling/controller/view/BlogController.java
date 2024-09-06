package cn.ling.controller.view;

import cn.ling.annotation.VisitLogger;
import cn.ling.constant.CommonConstants;
import cn.ling.constant.JwtConstants;
import cn.ling.enums.VisitBehavior;
import cn.ling.model.dto.BlogPasswordDTO;
import cn.ling.model.vo.BlogDetailVO;
import cn.ling.model.vo.BlogInfoVO;
import cn.ling.model.vo.PageResultVO;
import cn.ling.model.vo.SearchBlogVO;
import cn.ling.service.BlogService;
import cn.ling.util.JwtUtils;
import cn.ling.util.StringUtils;
import cn.ling.util.common.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 博客相关
 */
@RestController
public class BlogController {

	private final BlogService blogService;

	public BlogController(BlogService blogService) {
		this.blogService = blogService;
	}

	/**
	 * 按置顶、创建时间排序 分页查询博客简要信息列表
	 *
	 * @param pageNum 页码
	 * @return Result
	 */
	@VisitLogger(VisitBehavior.INDEX)
	@GetMapping("/blogs")
	public Result blogs(@RequestParam(defaultValue = "1") Integer pageNum) {
		PageResultVO<BlogInfoVO> pageResultVO = blogService.getBlogInfoListByIsPublished(pageNum);
		return Result.success("请求成功", pageResultVO);
	}

	/**
	 * 按id获取公开博客详情
	 *
	 * @param id  博客id
	 * @param jwt 密码保护文章的访问Token
	 * @return Result
	 */
	@VisitLogger(VisitBehavior.BLOG)
	@GetMapping("/blog")
	public Result getBlog(@RequestParam Long id,
	                      @RequestHeader(value = "Authorization", defaultValue = "") String jwt) {
		BlogDetailVO blog = blogService.getBlog(id, jwt);
		return Result.success("获取成功", blog);
	}

	/**
	 * 校验受保护文章密码是否正确，正确则返回jwt
	 *
	 * @param blogPasswordDTO 博客id、密码
	 * @return Result
	 */
	@VisitLogger(VisitBehavior.CHECK_PASSWORD)
	@PostMapping("/checkBlogPassword")
	public Result checkBlogPassword(@RequestBody BlogPasswordDTO blogPasswordDTO) {
		String password = blogService.getBlogPassword(blogPasswordDTO.getBlogId());
		if (password.equals(blogPasswordDTO.getPassword())) {
			//生成有效时间一个月的Token
			String jwt = JwtUtils.generateToken(blogPasswordDTO.getBlogId().toString(), 1000 * 3600 * 24 * 30L, JwtConstants.SECRET_KEY);
			return Result.success("密码正确", jwt);
		} else {
			return Result.exception(403, "密码错误");
		}
	}

	/**
	 * 按关键字根据文章内容搜索公开且无密码保护的博客文章
	 *
	 * @param query 关键字字符串
	 * @return Result
	 */
	@VisitLogger(VisitBehavior.SEARCH)
	@GetMapping("/searchBlog")
	public Result searchBlog(@RequestParam String query) {
		//校验关键字字符串合法性
		if (StringUtils.isEmpty(query) || StringUtils.hasSpecialChar(query) || query.trim().length() > CommonConstants.TWENTY) {
			return Result.error("参数错误");
		}
		List<SearchBlogVO> searchBlogVOList = blogService.getSearchBlogListByQueryAndIsPublished(query.trim());
		return Result.success("获取成功", searchBlogVOList);
	}
}
