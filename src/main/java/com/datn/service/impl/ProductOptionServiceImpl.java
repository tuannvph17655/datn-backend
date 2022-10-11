package com.datn.service.impl;

import com.datn.dto.customer.product.ColorResponse;
import com.datn.dto.customer.product.product_option.ProductOptionIdReq;
import com.datn.dto.customer.product.product_option.ProductOptionIdRes;
import com.datn.dto.customer.size.response.SizeResponse;
import com.datn.entity.ProductOptionEntity;
import com.datn.service.ProductOptionService;
import com.datn.utils.base.PuddyException;
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
}
