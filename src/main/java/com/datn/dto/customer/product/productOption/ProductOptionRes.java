package com.datn.dto.customer.product.productOption;

import com.datn.entity.ProductOptionEntity;
import com.datn.utils.common.BeanUtils;
import com.datn.utils.common.CopyUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Data
@Builder
@Accessors(chain = true)
public class ProductOptionRes {
    private String id;

    private String productId;

    private String colorId;

    private  String colorName;

    private Long qty;

    private Long price;

    private String image;

    private Boolean active;

    private String sizeId;
    private  String sizeName;

    public static ProductOptionRes of(ProductOptionEntity source) {
        ProductOptionRes target = ProductOptionRes.builder().build();
        CopyUtils.copy(source,target,"colorName","sizeName");
        return target;
    }

}
