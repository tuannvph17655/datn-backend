package com.datn.utils.base.enum_dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiscountDto {
	private String id;
	private String des;
	private String code;
	private String startDate;
	private String endDate;
	private Long prerequisiteValue;
	private Long percentDiscount;
	private String status;
	private Boolean deleted;
	private Long eventId;
}
