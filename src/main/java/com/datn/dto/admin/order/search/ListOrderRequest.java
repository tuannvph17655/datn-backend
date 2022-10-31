package com.datn.dto.admin.order.search;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ListOrderRequest {

    private String startDate;
    private String endDate;
    private String totalPrice;
    private Boolean payed;
    private String statusValue;
    private String textSearch;
}
