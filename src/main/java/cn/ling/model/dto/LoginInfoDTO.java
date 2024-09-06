package cn.ling.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 登录账号密码
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LoginInfoDTO {
	private String username;
	private String password;
}
