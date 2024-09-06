package cn.ling.controller.admin;

import cn.ling.constant.CommonConstants;
import cn.ling.constant.JwtConstants;
import cn.ling.entity.User;
import cn.ling.model.dto.LoginInfoDTO;
import cn.ling.service.UserService;
import cn.ling.util.JwtUtils;
import cn.ling.util.common.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 后台登录
 */
@RestController
@RequestMapping("/admin")
public class LoginAdminController {

	private final UserService userService;

	public LoginAdminController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 登录成功后，签发博主身份Token
	 *
	 * @param loginInfoDTO loginInfoDTO
	 * @return Result
	 */
	@PostMapping("/login")
	public Result login(@RequestBody LoginInfoDTO loginInfoDTO) {
		User user = userService.findUserByUsernameAndPassword(loginInfoDTO.getUsername(), loginInfoDTO.getPassword());
		if (!CommonConstants.ROLE_ADMIN.equals(user.getRole())) {
			return Result.exception(403, "无权限");
		}
		user.setPassword(null);
		String jwt = JwtUtils.generateToken("admin:" + user.getUsername(), JwtConstants.EXPIRE_TIME, JwtConstants.SECRET_KEY);
		Map<String, Object> map = new HashMap<>(16);
		map.put("user", user);
		map.put("token", jwt);
		return Result.success("登录成功", map);
	}
}
