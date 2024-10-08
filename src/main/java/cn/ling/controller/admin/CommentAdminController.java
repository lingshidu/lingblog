package cn.ling.controller.admin;

import cn.ling.annotation.OperationLogger;
import cn.ling.entity.Blog;
import cn.ling.entity.Comment;
import cn.ling.service.BlogService;
import cn.ling.service.CommentService;
import cn.ling.util.StringUtils;
import cn.ling.util.common.Result;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 博客评论后台管理
 */
@RestController
@RequestMapping("/admin")
public class CommentAdminController {
	private final CommentService commentService;
	private final BlogService blogService;

	public CommentAdminController(CommentService commentService, BlogService blogService) {
		this.commentService = commentService;
		this.blogService = blogService;
	}

	/**
	 * 按页面和博客id分页查询评论List
	 * @param page     要查询的页面(博客文章or关于我...)
	 * @param blogId   如果是博客文章页面 需要提供博客id
	 * @param pageNum  页码
	 * @param pageSize 每页个数
	 * @return Result
	 */
	@GetMapping("/comments")
	public Result comments(@RequestParam(defaultValue = "") Integer page,
	                       @RequestParam(defaultValue = "") Long blogId,
	                       @RequestParam(defaultValue = "1") Integer pageNum,
	                       @RequestParam(defaultValue = "10") Integer pageSize) {
		String orderBy = "create_time desc";
		PageMethod.startPage(pageNum, pageSize, orderBy);
		List<Comment> comments = commentService.getListByPageAndParentCommentId(page, blogId, (long) -1);
		PageInfo<Comment> pageInfo = new PageInfo<>(comments);
		return Result.success("请求成功", pageInfo);
	}

	/**
	 * 获取所有博客id和title 供评论分类的选择
	 *
	 * @return Result
	 */
	@GetMapping("/blogIdAndTitle")
	public Result blogIdAndTitle() {
		List<Blog> blogs = blogService.getIdAndTitleList();
		return Result.success("请求成功", blogs);
	}

	/**
	 * 更新评论公开状态
	 * @param id        评论id
	 * @param published 是否公开
	 * @return Result
	 */
	@OperationLogger("更新评论公开状态")
	@PutMapping("/comment/published")
	public Result updatePublished(@RequestParam Long id, @RequestParam Boolean published) {
		commentService.updateCommentPublishedById(id, published);
		return Result.success("操作成功");
	}

	/**
	 * 更新评论接收邮件提醒状态
	 * @param id     评论id
	 * @param notice 是否接收提醒
	 * @return Result
	 */
	@OperationLogger("更新评论邮件提醒状态")
	@PutMapping("/comment/notice")
	public Result updateNotice(@RequestParam Long id, @RequestParam Boolean notice) {
		commentService.updateCommentNoticeById(id, notice);
		return Result.success("操作成功");
	}

	/**
	 * 按id删除该评论及其所有子评论
	 * @param id 评论id
	 * @return Result
	 */
	@OperationLogger("删除评论")
	@DeleteMapping("/comment")
	public Result delete(@RequestParam Long id) {
		commentService.deleteCommentById(id);
		return Result.success("删除成功");
	}

	/**
	 * 修改评论
	 * @param comment 评论实体
	 * @return Result
	 */
	@OperationLogger("修改评论")
	@PutMapping("/comment")
	public Result updateComment(@RequestBody Comment comment) {
		if (StringUtils.isEmpty(comment.getNickname(), comment.getAvatar(), comment.getEmail(), comment.getIp(), comment.getContent())) {
			return Result.error("参数有误");
		}
		commentService.updateComment(comment);
		return Result.success("评论修改成功");
	}
}
