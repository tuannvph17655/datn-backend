package com.datn.dto.admin.voucher;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
public class VoucherDto {
    private String id;
    private String des;
    private String code;
    private Date startDate;
    private Date endDate;
    private BigDecimal prerequisiteValue;
    private String status;
    private BigDecimal active;
}
