package com.datn.service.impl;

import com.datn.dto.customer.product.ColorResponse;
import com.datn.entity.ColorEntity;
import com.datn.entity.ProductEntity;
import com.datn.service.ColorService;
import com.datn.utils.base.PuddyException;
import com.datn.utils.base.PuddyRepository;
import com.datn.utils.base.enum_dto.ColorDto;
import com.datn.utils.base.rest.CurrentUser;
import com.datn.utils.base.rest.ResData;
import com.datn.utils.common.JsonUtils;
import com.datn.utils.common.UidUtils;
import com.datn.utils.constants.PuddyCode;
import com.datn.utils.validator.auth.AuthValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ColorServiceImpl implements ColorService {
    private final PuddyRepository repository;
//    @Override
//    public ResData<List<ColorResponse>> getListColor() {
//        List<ColorResponse> color = repository.colorRepository.findAllColor();
//        return new ResData<>(color, PuddyCode.OK);
//    }
//
//    @Override
//    @Transactional
//    public ResData<String> create(CurrentUser currentUser, ColorDto dto) {
//        AuthValidator.checkAdmin(currentUser);
//        ProductEntity products = repository.productRepository.findById(dto.getId()).orElseThrow(
//                ()->  new PuddyException(PuddyCode.PRODUCT_NOT_FOUND)
//        );
//
//        ColorEntity color = ColorEntity.builder()
//                .id(UidUtils.generateUid())
//                .name(dto.getName().trim())
//                .hex(dto.getHex().trim())
//                .active(Boolean.TRUE)
//                .build();
//        repository.colorRepository.save(color);
//        log.info("create finished at {} with response: {}", new Date(), JsonUtils.toJson(color));
//        return new ResData<>(color.getId(), PuddyCode.OK);
//    }
//
//    @Override
//    @Transactional
//    public ResData<String> delete(CurrentUser currentUser, ColorDto dto) {
//        AuthValidator.checkAdmin(currentUser);
//        if (dto.getId() == null || Boolean.FALSE.equals(repository.colorRepository.findByIdAndActive(dto.getId(), Boolean.TRUE))) {
//            throw new PuddyException(PuddyCode.COLOR_NOT_FOUND);
//        }
//        ColorEntity color = repository.colorRepository.findByIdAndActive(dto.getId(), Boolean.TRUE);
//        color.setActive(Boolean.FALSE);
//        repository.colorRepository.save(color);
//        log.info("delete finished at {} with response: {}", new Date(), JsonUtils.toJson(color));
//        return new ResData<>(color.getId(), PuddyCode.OK);
//    }
//
//    @Override
//    @Transactional
//    public ResData<String> update(CurrentUser currentUser, ColorDto dto) {
//        AuthValidator.checkAdmin(currentUser);
//        if (dto.getId() == null || Boolean.FALSE.equals(repository.colorRepository.existsByIdAndActive(dto.getId(), Boolean.TRUE))) {
//            throw new PuddyException(PuddyCode.COLOR_NOT_FOUND);
//        }
//        ColorEntity color = repository.colorRepository.findByIdAndActive(dto.getId(), Boolean.TRUE);
//        color.setName(dto.getName().trim());
//        color.setHex(dto.getHex().trim());
//        repository.colorRepository.save(color);
//        log.info("update finished at {} with response: {}", new Date(), JsonUtils.toJson(color));
//        return new ResData<>(color.getId(), PuddyCode.OK);
//    }
}
