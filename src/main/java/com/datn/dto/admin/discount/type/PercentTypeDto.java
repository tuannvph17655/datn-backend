package com.datn.dto.admin.discount.type;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

@Data
@JsonTypeName("percent")
public class PercentTypeDto extends DiscountTypeDto {

    /**
     * Giá trị km
     */
    private String percentageValue;

    /**
     * Giá trị giảm tối đa
     */
    private String valueLimitAmount;
}
