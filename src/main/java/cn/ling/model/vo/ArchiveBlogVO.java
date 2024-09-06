package cn.ling.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 归档页面博客简要信息
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ArchiveBlogVO {
	private Long id;
	private String title;
	private String day;
	private String password;
	private Boolean privacy;
}
