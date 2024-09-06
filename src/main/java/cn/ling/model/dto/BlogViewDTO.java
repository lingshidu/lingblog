package cn.ling.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 博客浏览量
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BlogViewDTO {
	private Long id;
	private Integer views;
}
