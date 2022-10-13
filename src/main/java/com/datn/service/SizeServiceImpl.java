package com.datn.service;

import com.datn.dto.customer.size.response.SizeResponse;
import com.datn.utils.base.PuddyRepository;
import com.datn.utils.base.rest.ResData;
import com.datn.utils.constants.PuddyCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SizeServiceImpl implements SizeService {
    private final PuddyRepository repository;

    @Override
    public ResData<List<SizeResponse>> getAllSize() {
        List<SizeResponse> size = repository.sizeRepository.getAllSize();

        return new ResData<>(size,  PuddyCode.OK);
    }
}
