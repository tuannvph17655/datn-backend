package com.datn.service.impl;

import com.datn.dto.customer.event.EventsResponse;
import com.datn.repository.EventsRepository;
import com.datn.service.EventsService;
import com.datn.utils.base.PuddyRepository;
import com.datn.utils.base.rest.ResData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventsServiceImpl implements EventsService {
	private final PuddyRepository repository;

	@Override
	public ResData<List<EventsResponse>> getAllEvent() {
		List<EventsResponse> event = repository.eventsRepository.getAllEvent();
		return null;
	}
}
