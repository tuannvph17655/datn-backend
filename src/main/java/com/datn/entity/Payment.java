package com.datn.entity;


import com.datn.dtos.request.PaymentRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Basic
    @Column(name = "code", nullable = false, length = 25)
    private String code;

    @Basic
    @Column(name = "active", nullable = false)
    private boolean active;

    public static Payment from(PaymentRequest paymentRequest){
        return Payment.builder()
                .name(paymentRequest.getName())
                .code(paymentRequest.getCode())
                .active(true)
                .build();
    }
}
