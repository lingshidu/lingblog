package cn.ling.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 最新推荐博客
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class NewBlogVO {
	private Long id;
	private String title;
	private String password;
	private Boolean privacy;
}
