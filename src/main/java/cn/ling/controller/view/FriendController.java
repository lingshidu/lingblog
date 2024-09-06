package cn.ling.controller.view;

import cn.ling.annotation.VisitLogger;
import cn.ling.enums.VisitBehavior;
import cn.ling.model.vo.FriendInfoVO;
import cn.ling.model.vo.FriendVO;
import cn.ling.service.FriendService;
import cn.ling.util.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 友链
 */
@RestController
public class FriendController {
	private final FriendService friendService;

	public FriendController(FriendService friendService) {
		this.friendService = friendService;
	}

	/**
	 * 获取友链页面
	 * @return Result
	 */
	@VisitLogger(VisitBehavior.FRIEND)
	@GetMapping("/friends")
	public Result friends() {
		List<FriendVO> friendVOList = friendService.getFriendVOList();
		FriendInfoVO friendInfoVO = friendService.getFriendInfo(true, true);
		Map<String, Object> map = new HashMap<>(16);
		map.put("friendList", friendVOList);
		map.put("friendInfo", friendInfoVO);
		return Result.success("获取成功", map);
	}

	/**
	 * 按昵称增加友链浏览次数
	 *
	 * @param nickname 友链昵称
	 * @return Result
	 */
	@VisitLogger(VisitBehavior.CLICK_FRIEND)
	@PostMapping("/friend")
	public Result addViews(@RequestParam String nickname) {
		friendService.updateViewsByNickname(nickname);
		return Result.success("请求成功");
	}
}
