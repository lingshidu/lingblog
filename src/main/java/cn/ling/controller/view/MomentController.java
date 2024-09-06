package cn.ling.controller.view;

import cn.ling.annotation.AccessLimit;
import cn.ling.annotation.VisitLogger;
import cn.ling.constant.JwtConstants;
import cn.ling.entity.Moment;
import cn.ling.entity.User;
import cn.ling.enums.VisitBehavior;
import cn.ling.model.vo.PageResultVO;
import cn.ling.service.MomentService;
import cn.ling.service.impl.UserServiceImpl;
import cn.ling.util.JwtUtils;
import cn.ling.util.common.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

/**
 * 动态
 */
@RestController
public class MomentController {

	private final MomentService momentService;
	private final UserServiceImpl userService;

	public MomentController(MomentService momentService, UserServiceImpl userService) {
		this.momentService = momentService;
		this.userService = userService;
	}

	/**
	 * 分页查询动态List
	 *
	 * @param pageNum 页码
	 * @param jwt     博主访问Token
	 * @return Result
	 */
	@VisitLogger(VisitBehavior.MOMENT)
	@GetMapping("/moments")
	public Result moments(@RequestParam(defaultValue = "1") Integer pageNum,
	                      @RequestHeader(value = "Authorization", defaultValue = "") String jwt) {
		boolean adminIdentity = false;
		if (JwtUtils.judgeTokenIsExist(jwt)) {
			try {
				String subject = JwtUtils.getTokenBody(jwt, JwtConstants.SECRET_KEY).getSubject();
				//博主身份Token
				if (subject.startsWith(JwtConstants.ADMIN_PREFIX)) {
					String username = subject.replace("admin:", "");
					User admin = (User) userService.loadUserByUsername(username);
					if (admin != null) {
						adminIdentity = true;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		PageInfo<Moment> pageInfo = new PageInfo<>(momentService.getMomentVOList(pageNum, adminIdentity));
		PageResultVO<Moment> pageResultVO = new PageResultVO<>(pageInfo.getPages(), pageInfo.getList());
		return Result.success("获取成功", pageResultVO);
	}

	/**
	 * 给动态点赞
	 * 简单限制一下点赞
	 *
	 * @param id 动态id
	 * @return Result
	 */
	@AccessLimit(seconds = 86400, maxCount = 1, msg = "不可以重复点赞哦")
	@VisitLogger(VisitBehavior.LIKE_MOMENT)
	@PostMapping("/moment/like/{id}")
	public Result like(@PathVariable Long id) {
		momentService.addLikeByMomentId(id);
		return Result.success("点赞成功");
	}
}
