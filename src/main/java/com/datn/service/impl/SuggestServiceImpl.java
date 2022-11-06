package com.datn.service.impl;

import com.datn.dto.customer.product.ColorResponse;
import com.datn.dto.customer.suggest.SuggestDto;
import com.datn.dto.customer.suggest.SuggestResponse;
import com.datn.service.SuggestService;
import com.datn.utils.base.PuddyRepository;
import com.datn.utils.base.rest.CurrentUser;
import com.datn.utils.base.rest.ResData;
import com.datn.utils.constants.PuddyCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SuggestServiceImpl implements SuggestService {
    private final PuddyRepository repository;

    @Override
    public ResData<List<SuggestResponse>> getListSuggest() {
        List<SuggestResponse> suggest = repository.suggestRepository.findAllSuggest(id);
        return new ResData<>(color, PuddyCode.OK);
    }
    @Override
    public ResData<List<ColorResponse>> getListColor() {
        List<ColorResponse> color = repository.colorRepository.findAllColor();
        return new ResData<>(color, PuddyCode.OK);
    }

    @Override
    public ResData<String> create(CurrentUser currentUser, SuggestDto dto) {
        return null;
    }

    @Override
    public ResData<String> delete(CurrentUser currentUser, SuggestDto dto) {
        return null;
    }

    @Override
    public ResData<String> update(CurrentUser currentUser, SuggestDto dto) {
        return null;
    }
}
