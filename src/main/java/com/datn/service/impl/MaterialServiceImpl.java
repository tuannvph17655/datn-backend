package com.datn.service.impl;

import com.datn.entity.MaterialEntity;
import com.datn.entity.ProductEntity;
import com.datn.service.MaterialService;
import com.datn.utils.base.PuddyException;
import com.datn.utils.base.PuddyRepository;
import com.datn.utils.base.enum_dto.MaterialDto;
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
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MaterialServiceImpl implements MaterialService {

    private final PuddyRepository repository;

    @Override
    @Transactional
    public ResData<String> create(CurrentUser currentUser, MaterialDto dto) {
        //dasdas
        AuthValidator.checkAdmin(currentUser);
        Optional<ProductEntity> products = Optional.ofNullable(repository.productRepository.findById(dto.getId()).orElseThrow(
                () -> new PuddyException(PuddyCode.BAD_REQUEST, "Không tìm thấy Sản phầm tương ứng")
        ));

        MaterialEntity material = MaterialEntity.builder()
                .id(UidUtils.generateUid())
                .name(dto.getName().trim())
                .active(Boolean.TRUE)
                .build();
        repository.materialRepository.save(material);
        log.info("creat finished at {} with response: {}", new Date(), JsonUtils.toJson(material));
        return new ResData<>(material.getId(), PuddyCode.OK);
    }

    @Override
    public ResData<String> delete(CurrentUser currentUser, MaterialDto dto) {
        AuthValidator.checkAdmin(currentUser);
        if (dto.getId() == null) {
            throw new PuddyException(PuddyCode.MATERIAL_NOT_FOUND);
        } else {
            repository.colorRepository.findByIdAndActive(dto.getId(), Boolean.TRUE);
        }
        MaterialEntity material = repository.materialRepository.findByIdAndActive(dto.getId(),Boolean.TRUE);
        material.setActive(Boolean.FALSE);
        repository.materialRepository.save(material);
        log.info("delete finished at {} with response: {}", new Date(), JsonUtils.toJson(material));
        return new ResData<>(material.getId(), PuddyCode.OK);
    }

    @Override
    public ResData<String> update(CurrentUser currentUser, MaterialDto dto) {
        AuthValidator.checkAdmin(currentUser);
        if (dto.getId() == null || Boolean.FALSE.equals(repository.materialRepository.existsByIdAndActive(dto.getId(), Boolean.TRUE))) {
            throw new PuddyException(PuddyCode.MATERIAL_NOT_FOUND);
        }
        MaterialEntity material = repository.materialRepository.findByIdAndActive(dto.getId(), Boolean.TRUE);
        material.setName(dto.getName().trim());
        repository.materialRepository.save(material);
        log.info("update finish at {} with response: {}" ,new Date(), JsonUtils.toJson(material));
        return new ResData<>(material.getId(), PuddyCode.OK);
    }
}
