package com.datn.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders_detail")
public class OrdersDetail extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "product_name", nullable = false, length = 255)
    private String productName;
    @Basic
    @Column(name = "order_id", nullable = false)
    private long orderId;
    @Basic
    @Column(name = "quantity", nullable = false)
    private int quantity;

}
