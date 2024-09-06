package cn.ling.model.dto;

import lombok.*;

import java.util.Date;

/**
 * 访客更新DTO
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class VisitLogUuidTimeDTO {
	private String uuid;
	private Date time;
	private Integer pv;
}
