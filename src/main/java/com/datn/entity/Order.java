package com.datn.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order  {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Basic
    @Column(name = "address_id", nullable = false)
    private Long addressId;
    @Basic
    @Column(name = "note", nullable = true, length = 255)
    private String note;
    @Basic
    @Column(name = "payed", nullable = false)
    private boolean payed;
    @Basic
    @Column(name = "payment", nullable = false, length = 255)
    private String payment;
    @Basic
    @Column(name = "ship_price", nullable = false, precision = 0)
    private double shipPrice;
    @Basic
    @Column(name = "total_discount", nullable = false, precision = 0)
    private double totalDiscount;
    @Basic
    @Column(name = "code", nullable = false, length = 255)
    private String code;
    @Basic
    @Column(name = "total", nullable = false, precision = 0)
    private double total;
    @Basic
    @Column(name = "status", nullable = true, length = 255)
    private String status;
    @Basic
    @Column(name = "voucher_id", nullable = false)
    private Long voucherId;

}
