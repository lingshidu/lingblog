package cn.ling.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 分类和博客数量
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CategoryBlogCountVO {
	private Long id;
	/**
	 * 分类名
	 */
	private String name;
	/**
	 * 分类下博客数量
	 */
	private Integer value;
}
