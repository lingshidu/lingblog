package cn.ling.model.vo;

import cn.ling.model.bean.Favorite;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 侧边栏资料卡
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class IntroductionVO {
	private String avatar;
	private String name;
	private String github;
	private String qq;
	private String bilibili;
	private String netease;
	private String email;

	private List<String> rollText;
	private List<Favorite> favorites;

}
