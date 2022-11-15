package com.datn.service.impl;

import com.datn.dto.customer.product.ColorResponse;
import com.datn.dto.customer.product.productOption.ProductOptionIdReq;
import com.datn.dto.customer.product.productOption.ProductOptionIdRes;
import com.datn.dto.customer.product.productOption.ProductOptionReq;
import com.datn.dto.customer.product.productOption.ProductOptionRes;
import com.datn.dto.customer.size.response.SizeResponse;
import com.datn.entity.ColorEntity;
import com.datn.entity.ProductOptionEntity;
import com.datn.entity.SizeEntity;
import com.datn.service.ProductOptionService;
import com.datn.utils.base.PuddyException;
import com.datn.utils.base.PuddyRepository;
import com.datn.utils.base.rest.CurrentUser;
import com.datn.utils.base.rest.PageData;
import com.datn.utils.base.rest.PageReq;
import com.datn.utils.base.rest.ResData;
import com.datn.utils.common.PageableUtils;
import com.datn.utils.constants.PuddyCode;
import com.datn.utils.validator.auth.AuthValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductOptionServiceImpl implements ProductOptionService {

    private final PuddyRepository repository;

    @Override
    public ResData<ProductOptionIdRes> findProductOptionId(ProductOptionIdReq req) {
        ProductOptionEntity productOption = repository.productOptionRepository.findBySizeAndColorId(req.getSizeId(), req.getColorId(), req.getProductId());
        if (productOption == null) {
            throw new PuddyException(PuddyCode.INTERNAL_SERVER);
        }
        ProductOptionIdRes res = ProductOptionIdRes.builder()
                .productOptionId(productOption.getId())
                .quantity(productOption.getQty())
                .price(productOption.getPrice())
                .build();
        return new ResData<>(res, PuddyCode.OK);
    }


    @Override
    public ResData<List<ColorResponse>> findColorNameBySize(String sizeId, String productId) {
        List<ColorResponse> color = repository.productOptionRepository.getListColorNameBySize(sizeId, productId);
        log.info("----------------" + color);
        return new ResData<>(color, PuddyCode.OK);
    }

    @Override
    public ResData<List<SizeResponse>> findSizeByProductId(String productId) {
        List<SizeResponse> size = repository.productOptionRepository.findListSizeByProductId(productId);
        return new ResData<>(size, PuddyCode.OK);
    }

    @Override
    public PageData<ProductOptionRes> findAllByProductId(CurrentUser currentUser, ProductOptionReq productOptionReq) {
        AuthValidator.checkAdmin(currentUser);
        Pageable pageable = PageableUtils.getPageable(productOptionReq.getPageReq());
        List<ProductOptionRes> productOptionRes = repository.productOptionRepository
                .findByProductId(productOptionReq.getProductId())
                .stream().map(s -> {
                    SizeEntity sizeEntity = repository.sizeRepository.getById(s.getSizeId());
                    ColorEntity colorEntity = repository.colorRepository.getById(s.getSizeId());
                    return ProductOptionRes.of(s).setSizeName(sizeEntity.getName()).setColorName(colorEntity.getName());
                })
                .collect(Collectors.toList());
        Page<ProductOptionRes> result = new PageImpl<>(productOptionRes, pageable, productOptionRes.size());
        return PageData.setResult(result.getContent()
                , result.getNumber()
                , result.getSize()
                , result.getTotalElements());
    }
}
