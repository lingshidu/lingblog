package cn.ling.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 站点设置
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SiteSetting {
	private Long id;
	private String nameEn;
	private String nameZh;
	private String value;
	private Integer type;
}
