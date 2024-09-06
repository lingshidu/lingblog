package cn.ling.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 友链DTO
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FriendDTO {
	private Long id;
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
	/**
	 * 公开或隐藏
	 */
	private Boolean published;
}
