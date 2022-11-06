package com.datn.dto.admin.order.search;

import lombok.Builder;
import lombok.Data;

@Data
public class ListOrderRequest {

    private String startDate = "";
    private String endDate = "";
    private String totalPrice = "";
    private String payed = "";
    private String statusValue = "";
    private String textSearch = "";
    private int page = 1;
    private int size = 30;
}
