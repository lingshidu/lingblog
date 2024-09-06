package cn.ling.entity;

import lombok.*;

/**
 * 城市访客数量
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CityVisitor {
	/**
	 * 城市名称
	 */
	private String city;
	/**
	 * 独立访客数量
	 */
	private Integer uv;
}
