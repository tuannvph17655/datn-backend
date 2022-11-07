package com.datn.service;

import com.datn.dto.customer.event.EventsResponse;
import com.datn.utils.base.rest.ResData;

import java.util.List;

public interface EventsService {
	ResData<List<EventsResponse>> getAllEvent();
}
