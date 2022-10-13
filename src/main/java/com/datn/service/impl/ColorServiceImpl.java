package com.datn.service.impl;

import com.datn.dto.customer.product.ColorResponse;
import com.datn.service.ColorService;
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
public class ColorServiceImpl implements ColorService {
    private final PuddyRepository repository;
    @Override
    public ResData<List<ColorResponse>> getListColor() {
        List<ColorResponse> color = repository.colorRepository.findAllColor();
        return new ResData<>(color, PuddyCode.OK);
    }
}
