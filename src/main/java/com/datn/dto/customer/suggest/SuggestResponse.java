package com.datn.dto.customer.suggest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SuggestResponse {
    private String suggestId;

    private String categoryId;

    private String sizeId;

    private String suggestBodyWeightId;

    private String suggestBodyHeightId;
}
