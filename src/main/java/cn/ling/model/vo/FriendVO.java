package cn.ling.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 友链VO
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FriendVO {
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 站点
	 */
	private String website;
	/**
	 * 头像
	 */
	private String avatar;
}
