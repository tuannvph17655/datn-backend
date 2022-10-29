package com.datn.dto.admin.detail;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class DetailRes {
    private String id;
    private List<ItemDto> items;
    private PromotionDto promotions;
    private PriceResult priceResult;
    private List<String> history;
    private OrderInfoRes orderInfo;

    @Data
    @Builder
    public static class OrderInfoRes {
        private String nameOfRecipient;
        private String phoneNumber;
        private String note;
        private String shipAddress;
        private String paymentMethod;
        private boolean payed;
        private String statusOrder;
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
        private Date createDate;
    }
}
