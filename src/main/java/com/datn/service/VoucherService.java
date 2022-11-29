package com.datn.service;

import com.datn.dto.customer.discount.VoucherResponse;
import com.datn.utils.base.enum_dto.DiscountDto;
import com.datn.utils.base.rest.ResData;

import java.util.List;

public interface VoucherService {
	ResData<List<VoucherResponse>> getListVoucher();

	ResData<String> create(DiscountDto dto);

	ResData<String> update(DiscountDto dto);
}
