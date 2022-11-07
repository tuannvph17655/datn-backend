package com.datn.service;

import com.datn.dto.customer.event.EventsResponse;
import com.datn.utils.base.enum_dto.EventDto;
import com.datn.utils.base.rest.CurrentUser;
import com.datn.utils.base.rest.ResData;
import java.util.List;

public interface EventsService {
	ResData<List<EventsResponse>> getAllEvent();

	ResData<String> create(CurrentUser currentUser, EventDto dto);

	ResData<String> delete(CurrentUser currentUser, EventDto dto);

	ResData<String> update(CurrentUser currentUser, EventDto dto);
}
