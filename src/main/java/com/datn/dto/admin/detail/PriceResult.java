package com.datn.dto.admin.detail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PriceResult {
    private Long shop;

    private Long ship;
    private Long discount;

    //tổng
    private Long total;
}
