package com.datn.dto.admin.detail;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class DetailRes {
    private String id;
    private List<ItemDto> items;
    private PromotionDto promotions;
    private ResultDto result;
    private List<String> history;
}
