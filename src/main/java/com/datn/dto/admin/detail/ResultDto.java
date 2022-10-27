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
public class ResultDto {
    private PriceDto shop;

    private PriceDto ship;

    //tổng
    private String total;
}
