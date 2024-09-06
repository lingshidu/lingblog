package cn.ling.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 博客分类
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Category {
	private Long id;
	/**
	 * 分类名称
	 */
	private String name;
	/**
	 * 该分类下的博客文章
	 */
	private List<Blog> blogs = new ArrayList<>();
}
