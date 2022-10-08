package com.datn.dtos.response;

import com.datn.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {
    private Long id;
    private String name;
    private String code;
    private boolean active;

    public static PaymentResponse from(Payment payment){
        PaymentResponse paymentResponse = PaymentResponse
                .builder()
                .id(payment.getId())
                .name(payment.getName())
                .code(payment.getCode())
                .active(payment.isActive())
                .build();
        return paymentResponse;
    }
}
