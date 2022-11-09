package com.datn.service.impl;

import com.datn.dto.customer.discount.DiscountResponse;
import com.datn.entity.DiscountEntity;
import com.datn.service.DiscountService;
import com.datn.utils.base.PuddyRepository;
import com.datn.utils.base.enum_dto.DiscountDto;
import com.datn.utils.base.rest.ResData;
import com.datn.utils.common.JsonUtils;
import com.datn.utils.common.UidUtils;
import com.datn.utils.constants.PuddyCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiscountServiceImpl implements DiscountService {
	private final PuddyRepository repository;

	@Override
	public ResData<List<DiscountResponse>> getListDiscount() {
		List<DiscountResponse> discount = repository.discountRepository.findAllDiscount();
		return new ResData<>(discount, PuddyCode.OK);
	}

	@Override
	public ResData<String> create(DiscountDto dto) {
		DiscountEntity discount = DiscountEntity.builder()
				.id(UidUtils.generateUid())
				.des(dto.getDes().trim())
				.code(dto.getCode().trim())
				.prerequisiteValue(dto.getPrerequisiteValue().longValue())
				.percentDiscount(dto.getPercentDiscount().longValue())
				.startDate(dto.getStartDate().trim())
				.endDate(dto.getEndDate().trim())
				.status(dto.getStatus().trim())
				.deleted(Boolean.TRUE)
				.eventId(dto.getEventId().longValue())
				.build();
		repository.discountRepository.save(discount);
		log.info("create finished at {} with response: {}", new Date(), JsonUtils.toJson(discount));
		return new ResData<>(discount.getId(), PuddyCode.OK);
	}

	@Override
	public ResData<String> update(DiscountDto dto) {
		DiscountEntity discount = DiscountEntity.builder()
				.id(dto.getId().trim())
				.des(dto.getDes().trim())
				.code(dto.getCode().trim())
				.prerequisiteValue(dto.getPrerequisiteValue().longValue())
				.percentDiscount(dto.getPercentDiscount().longValue())
				.startDate(dto.getStartDate().trim())
				.endDate(dto.getEndDate().trim())
				.status(dto.getStatus().trim())
				.deleted(Boolean.TRUE)
				.eventId(dto.getEventId().longValue())
				.build();
		repository.discountRepository.save(discount);
		log.info("create finished at {} with response: {}", new Date(), JsonUtils.toJson(discount));
		return new ResData<>(discount.getId(), PuddyCode.OK);
	}
}
