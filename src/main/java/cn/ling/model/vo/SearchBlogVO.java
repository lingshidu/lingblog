package cn.ling.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 关键字搜索博客
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SearchBlogVO {
	private Long id;
	private String title;
	private String content;
}
