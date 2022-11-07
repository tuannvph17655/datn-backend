package com.datn.dto.customer.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventsResponse {
	private Long eventId;
	private String eventName;
	private String status;
	private Date startDate;
	private String enDate;
}
