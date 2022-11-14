package com.datn.dto.customer.product.productOption;

import com.datn.entity.ProductOptionEntity;
import com.datn.utils.common.BeanUtils;
import com.datn.utils.common.CopyUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ProductOptionRes {
    private String id;

    private String productId;

    private String colorId;

    private Long qty;

    private Long price;

    private String image;

    private Boolean active;

    private String sizeId;

    public static ProductOptionRes of(ProductOptionEntity source) {
        ProductOptionRes target = ProductOptionRes.builder().build();
        CopyUtils.copy(source,target);
        return target;
    }

}
