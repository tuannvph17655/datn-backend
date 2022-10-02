package com.datn.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vouchers")
public class Voucher extends BaseEntity{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "event_id", nullable = false)
    private Long eventId;
    @Basic
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Basic
    @Column(name = "min_total", nullable = false, precision = 0)
    private double minTotal;
    @Basic
    @Column(name = "amount", nullable = true)
    private Integer amount;
    @Basic
    @Column(name = "discount", nullable = false, precision = 0)
    private double discount;
    @Basic
    @Column(name = "active", nullable = false)
    private boolean active;

}
