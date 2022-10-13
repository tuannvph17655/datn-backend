package com.datn.service;

import com.datn.dto.customer.size.response.SizeResponse;
import com.datn.utils.base.rest.ResData;

import java.util.List;

public interface SizeService {
    ResData<List<SizeResponse>> getAllSize();
}
