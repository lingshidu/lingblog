package cn.ling.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 标签和博客数量
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TagBlogCountVO {
	private Long id;
	/**
	 * 标签名
	 */
	private String name;
	/**
	 * 标签下博客数量
	 */
	private Integer value;
}
