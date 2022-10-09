package com.datn.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class OrderEntity {
    @Id
    private String id;

    @Column(name = "address_id")
    private String addressId;

    @Column(name = "user_id")
    private String userId;

    //Ghi chú
    private String note;

    //đã thanh toán chưa
    @Column(name = "payed ")
    private Boolean payed;

    //hình thức thanh toán
    private String payment;

    //tiền ship
    @Column(name = "ship_price")
    private Long shipPrice;


    @Column(name = "ship_type_id")
    private String shipTypeId;

    private String shipMethod;

    /**
     * mã đơn hàng: DH0001(dùng seq)
     */
    private String code;

    private String status;

    /**
     * tổng tiền
     */
    private Long total;

    private String discountId;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createdDate;

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date")
    private Date updatedDate;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;
}
