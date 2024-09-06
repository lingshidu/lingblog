package cn.ling.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 受保护文章密码DTO
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BlogPasswordDTO {
	private Long blogId;
	private String password;
}
