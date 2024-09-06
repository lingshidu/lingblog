package cn.ling.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 友链页面信息
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FriendInfoVO {
	private String content;
	private Boolean commentEnabled;
}
