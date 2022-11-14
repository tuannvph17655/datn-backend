package com.datn.service.impl;

import com.datn.dto.admin.category.CategoryDto;
import com.datn.dto.admin.category.CategoryReq;
import com.datn.dto.admin.category.CategoryRes;
import com.datn.dto.admin.category.CategoryRes4Admin;
import com.datn.entity.CategoryEntity;
import com.datn.entity.ProductEntity;
import com.datn.entity.ProductOptionEntity;
import com.datn.service.CategoryService;
import com.datn.utils.base.PuddyException;
import com.datn.utils.base.PuddyRepository;
import com.datn.utils.base.rest.CurrentUser;
import com.datn.utils.base.rest.PageData;
import com.datn.utils.base.rest.ResData;
import com.datn.utils.common.DateUtils;
import com.datn.utils.common.JsonUtils;
import com.datn.utils.common.PageableUtils;
import com.datn.utils.common.StringUtils;
import com.datn.utils.common.UidUtils;
import com.datn.utils.constants.PuddyCode;
import com.datn.utils.validator.admin.category.CategoryValidator;
import com.datn.utils.validator.auth.AuthValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static com.datn.message.ResponseMessageText.CATEGORY_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final PuddyRepository repository;

    @Override
    @Transactional
    public PageData<CategoryRes> search(CurrentUser currentUser, CategoryReq req) throws PuddyException {
        AuthValidator.checkAdmin(currentUser);
        if (StringUtils.isNullOrEmpty(req.getTextSearch())) {
            req.setTextSearch("");
        }
        String textSearch = req.getTextSearch().trim().toUpperCase(Locale.ROOT);
        Pageable pageable = PageableUtils.getPageable(req.getPageReq());
        Page<CategoryEntity> categoryPage = repository.categoryRepository.search(textSearch, req.getActive(), pageable);

        return PageData.setResult(categoryPage.getContent().stream().map(o -> CategoryRes.builder()
                        .id(o.getId())
                        .name(o.getName())
                        .des(o.getDes())
                        .active(o.getActive())
                        .createdDate(o.getCreatedDate())
                        .createdDateValue(o.getCreatedDate() == null ? null : DateUtils.toStr(o.getCreatedDate(), DateUtils.F_DDMMYYYY))
                        .productNumber(repository.productRepository.countByCategoryId(o.getId()))
                        .build()).collect(Collectors.toList()),
                categoryPage.getNumber(),
                categoryPage.getSize(),
                categoryPage.getTotalElements());
    }

    @Override
    public ResData<String> create(CurrentUser currentUser, CategoryDto dto) {
        AuthValidator.checkAdmin(currentUser);
        CategoryValidator.validCreate(dto);
                CategoryEntity categoryEntity = CategoryEntity.builder()
                        .id(UidUtils.generateUid())
                        .name(dto.getName())
                        .des(dto.getDes())
                        .active(Boolean.TRUE)
                        .build();
        repository.categoryRepository.save(categoryEntity);
        return new ResData<>(categoryEntity.getId(), PuddyCode.OK);
    }

    @Override
    @Transactional
    public ResData<String> delete(CurrentUser currentUser, CategoryDto dto) {
        AuthValidator.checkAdmin(currentUser);
        CategoryEntity category = repository.categoryRepository.findById(dto.getId()).orElseThrow(()-> new PuddyException(PuddyCode.BAD_REQUEST,CATEGORY_NOT_FOUND));
                category.setActive(Boolean.FALSE);
        repository.categoryRepository.save(category);
        log.info("create finished at {} with response: {}", new Date(), JsonUtils.toJson(category));
        return new ResData<>(category.getId(), PuddyCode.OK);
    }

    @Override
    @Transactional
    public ResData<String> update(CurrentUser currentUser, CategoryDto dto) {
        AuthValidator.checkAdmin(currentUser);
        if (Boolean.FALSE.equals(repository.categoryRepository.existsById(dto.getId()))) {
            throw new PuddyException(PuddyCode.CATEGORY_NOT_FOUND);
        }
        CategoryEntity category = repository.categoryRepository.findByIdAndActive(dto.getId(), Boolean.TRUE);
        category.setActive(Boolean.FALSE);
        repository.categoryRepository.save(category);
        List<ProductEntity> products = repository.productRepository.findByCategoryIdAndActive(category.getId(), Boolean.TRUE);
        if (Boolean.FALSE.equals(products.isEmpty())) {
            products.forEach(product -> {
                product.setActive(Boolean.FALSE);
                repository.productRepository.save(product);
                List<ProductOptionEntity> productOptions = repository.productOptionRepository.findByProductIdAndActive(product.getId(), Boolean.TRUE);
                if (!productOptions.isEmpty()) {
                    productOptions.forEach(po -> {
                        po.setActive(Boolean.FALSE);
                        repository.productOptionRepository.save(po);
                    });
                }
            });
        }
        log.info("delete finished at {} with response: {}", new Date(), JsonUtils.toJson(category));
        return new ResData<>(category.getId(), PuddyCode.OK);
    }

    @Override
    @Transactional
    public ResData<CategoryRes> detail(CurrentUser currentUser, CategoryDto dto) {
        AuthValidator.checkAdmin(currentUser);
        CategoryEntity category = repository.categoryRepository.findByIdAndActive(dto.getId(), true);
        if (null == category) {
            throw new PuddyException(PuddyCode.ERROR_NOT_FOUND);
        }
        return ResData.ok(CategoryRes.builder()
                .id(category.getId())
                .name(category.getName())
                .des(category.getDes())
                .active(category.getActive())
                .createdDate(category.getCreatedDate())
                .createdDateValue(category.getCreatedDate() == null ? null : DateUtils.toStr(category.getCreatedDate(), DateUtils.F_DDMMYYYY))
                .productNumber(repository.productRepository.countByCategoryId(category.getId()))
                .build());
    }

    @Override
    public ResData<CategoryRes4Admin> getAllCategory(CurrentUser currentUser) {
        return ResData.ok(repository.categoryRepository.findAllCategoryActive());
    }
}
