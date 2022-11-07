package com.datn.service.impl;

import com.datn.dto.customer.event.EventsResponse;
import com.datn.entity.EventEntity;
import com.datn.service.EventsService;
import com.datn.utils.base.PuddyException;
import com.datn.utils.base.PuddyRepository;
import com.datn.utils.base.enum_dto.EventDto;
import com.datn.utils.base.rest.CurrentUser;
import com.datn.utils.base.rest.ResData;
import com.datn.utils.common.JsonUtils;
import com.datn.utils.common.UidUtils;
import com.datn.utils.constants.PuddyCode;
import com.datn.utils.validator.auth.AuthValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventsServiceImpl implements EventsService {
	private final PuddyRepository repository;


	@Override
	public ResData<List<EventsResponse>> getAllEvent() {
		List<EventsResponse> event = repository.eventsRepository.getAllEvent();
		return new ResData<>(event, PuddyCode.OK);
	}

	@Override
	public ResData<String> create(CurrentUser currentUser, EventDto dto) {
		AuthValidator.checkAdmin(currentUser);
		EventEntity events = repository.eventsRepository.findById(dto.getId()).orElseThrow(
				()->  new PuddyException(PuddyCode.EVENT_NOT_FOUND_1)
		);
		EventEntity event = EventEntity.builder()
				.id(UidUtils.generateUid())
				.name(dto.getName().trim())
				.status(dto.getStatus().trim())
				.startDate(dto.getStartDate().trim())
				.endDate(dto.getEndDate().trim())
				.build();
		repository.eventsRepository.save(event);
		log.info("create finished at {} with response: {}", new Date(), JsonUtils.toJson(event));
		return new ResData<>(event.getId(), PuddyCode.OK);
	}


	@Override
	public ResData<String> delete(CurrentUser currentUser, EventDto dto) {
		return null;
	}

	@Override
	public ResData<String> update(CurrentUser currentUser, EventDto dto) {
		AuthValidator.checkAdmin(currentUser);
		EventEntity events = repository.eventsRepository.findById(dto.getId()).orElseThrow(
				()->  new PuddyException(PuddyCode.EVENT_NOT_FOUND)
		);
		EventEntity event = EventEntity.builder()
				.id(dto.getId().trim())
				.name(dto.getName().trim())
				.status(dto.getStatus().trim())
				.startDate(dto.getStartDate().trim())
				.endDate(dto.getEndDate().trim())
				.build();
		repository.eventsRepository.save(event);
		log.info("create finished at {} with response: {}", new Date(), JsonUtils.toJson(event));
		return new ResData<>(event.getId(), PuddyCode.OK);
	}


}
