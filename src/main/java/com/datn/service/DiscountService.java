package com.datn.service;

import com.datn.dto.customer.discount.DiscountResponse;
import com.datn.utils.base.enum_dto.DiscountDto;
import com.datn.utils.base.rest.ResData;

import java.util.List;

public interface DiscountService {
	ResData<List<DiscountResponse>> getListDiscount();

	ResData<String> create(DiscountDto dto);

	ResData<String> update(DiscountDto dto);
}
