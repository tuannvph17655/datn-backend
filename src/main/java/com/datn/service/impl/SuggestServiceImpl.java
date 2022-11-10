package com.datn.service.impl;

import com.datn.dto.admin.category.CategoryDto;
import com.datn.dto.customer.suggest.SuggestDto;
import com.datn.entity.BodyHeightEntity;
import com.datn.entity.BodyWeightEntity;
import com.datn.entity.SizeEntity;
import com.datn.entity.SuggestEntity;
import com.datn.service.SuggestService;
import com.datn.utils.base.PuddyException;
import com.datn.utils.base.PuddyRepository;
import com.datn.utils.base.rest.ResData;
import com.datn.utils.common.JsonUtils;
import com.datn.utils.constants.PuddyCode;
import com.datn.utils.validator.suggest.SuggestValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SuggestServiceImpl implements SuggestService {

    private final PuddyRepository repository;

//    @Override
//    public List<CategoryDto> getCategories() {
//        return repository.categoryRepository.findSuggestCategories();
//    }

    @Override
    public Object getSizeAvailable(SuggestDto dto) {
        log.info("SuggestServiceImpl getSizeAvailable start with dto: {}", JsonUtils.toJson(dto));
        log.info("SuggestServiceImpl getSizeAvailable validate");
        SuggestValidator.validGetSizeAvailable(dto);
        BodyHeightEntity height = repository.bodyHeightRepository.findByHeight(Long.valueOf(dto.getHeight()));
        BodyWeightEntity weight = repository.bodyWeightRepository.findByWeight(Long.valueOf(dto.getWeight()));
        if (height == null || weight == null) {
            throw new PuddyException(PuddyCode.INTERNAL_SERVER);
        }
        int code = height.getCode().getValue() - weight.getCode().getValue();
        SuggestEntity suggest;
        if (code < 0) {
            suggest = repository.suggestRepository.findByBodyWeightIdAndCategoryId(weight.getId(), dto.getCategoryId());
            if (suggest == null) {
                suggest = repository.suggestRepository.findByBodyWeightIdAndCategoryIdIsNull(weight.getId());
            }
        } else if (code == 0) {
            suggest = repository.suggestRepository.findByBodyWeightIdAndBodyHeightIdAndCategoryId(weight.getId(), height.getId(), dto.getCategoryId());
            if (suggest == null) {
                suggest = repository.suggestRepository.findByBodyWeightIdAndBodyHeightIdAndCategoryIdIsNull(weight.getId(), height.getId());
            }
        } else {
            suggest = repository.suggestRepository.findByBodyHeightIdAndCategoryId(height.getId(), dto.getCategoryId());
            if (suggest == null) {
                suggest = repository.suggestRepository.findByBodyHeightIdAndCategoryIdIsNull(height.getId());
            }
        }
        if (suggest == null) {
            throw new PuddyException(PuddyCode.SIZE_SUGGEST_NOT_FOUND);
        }
        SizeEntity size = repository.sizeRepository.findById(suggest.getSizeId()).orElseThrow(() ->
            new PuddyException(PuddyCode.SIZE_SUGGEST_NOT_FOUND)
        );
        log.info("SuggestServiceImpl getSizeAvailable finish with res: {}", JsonUtils.toJson(size));
        return ResData.ok(size);
    }
}
