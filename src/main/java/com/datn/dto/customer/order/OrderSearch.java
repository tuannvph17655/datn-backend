package com.datn.dto.customer.order;

import com.datn.utils.base.rest.PageReq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderSearch {
    private String textSearch;
    private String status;
    private PageReq pageReq;
}
