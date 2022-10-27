package com.datn.dto.admin.detail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PriceDto {
    private String name;
    private String price;
    private String discount;
    private String total;
}
