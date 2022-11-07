package com.datn.dto.customer.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventsResponse {
	private String eventId;
	private String eventName;
	private String status;
	private String startDate;
	private String endDate;
}
