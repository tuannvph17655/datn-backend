package com.datn.service.impl;

import com.datn.dto.admin.size.SizeReq;
import com.datn.dto.admin.size.SizeRes;
import com.datn.dto.customer.size.response.SizeResponse;
import com.datn.entity.ProductEntity;
import com.datn.entity.SizeEntity;
import com.datn.service.SizeService;
import com.datn.utils.base.PuddyException;
import com.datn.utils.base.PuddyRepository;
import com.datn.utils.base.enum_dto.SizeDto;
import com.datn.utils.base.rest.CurrentUser;
import com.datn.utils.base.rest.PageData;
import com.datn.utils.base.rest.ResData;
import com.datn.utils.common.JsonUtils;
import com.datn.utils.common.PageableUtils;
import com.datn.utils.common.UidUtils;
import com.datn.utils.constants.PuddyCode;
import com.datn.utils.validator.auth.AuthValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SizeServiceImpl implements SizeService {
    private final PuddyRepository repository;

    @Override
    public ResData<List<SizeResponse>> getAllSize() {
        List<SizeResponse> size = repository.sizeRepository.getAllSize();

        return new ResData<>(size, PuddyCode.OK);
    }

    @Override
    @Transactional
    public ResData<String> create(CurrentUser currentUser, SizeDto dto) {
        AuthValidator.checkAdmin(currentUser);
        ProductEntity products = repository.productRepository.findById(dto.getId()).orElseThrow(
                () -> new PuddyException(PuddyCode.PRODUCT_NOT_FOUND)
        );
        SizeEntity size = SizeEntity.builder()
                .id(UidUtils.generateUid())
                .code(dto.getCode().trim())
                .name(dto.getName().trim())
                .build();
        repository.sizeRepository.save(size);
        log.info("create finished at {} with response: {}", new Date(), JsonUtils.toJson(size));
        return new ResData<>(size.getId(), PuddyCode.OK);
    }

    @Override
    @Transactional
    public PageData<SizeRes> getAllSize4Admin(CurrentUser currentUser, SizeReq sizeReq) {
        AuthValidator.checkAdmin(currentUser);
        Pageable pageable = PageableUtils.getPageable(sizeReq.getPageReq());
        log.info("paging {}", pageable);
        Page<SizeEntity> sizeResponse = repository.sizeRepository.findAll(pageable);
        return PageData.setResult(sizeResponse.getContent().stream()
                        .map(o -> SizeRes.builder()
                                .id(o.getId())
                                .name(o.getName())
                                .code(o.getCode())
                                .status(o.getActive()).build()).collect(Collectors.toList()),
                sizeResponse.getNumber(),
                sizeResponse.getSize(),
                sizeResponse.getTotalElements());
    }

//    @Override
//    @Transactional
//    public ResData<String> delete(CurrentUser currentUser, SizeDto dto) {
//        return null;
//    }
//
//    @Override
//    @Transactional
//    public ResData<String> update(CurrentUser currentUser, SizeDto dto) {
//        return null;
//    }
}
