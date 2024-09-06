package cn.ling.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 博客动态
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Moment {
	private Long id;
	/**
	 * 动态内容
	 */
	private String content;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 点赞数量
	 */
	private Integer likes;
	/**
	 * 是否公开
	 */
	private Boolean published;
}
