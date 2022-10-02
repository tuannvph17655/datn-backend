package com.datn.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_logs")
public class OrderLog extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "order_id", nullable = false)
    private Long orderId;
    @Basic
    @Column(name = "note", nullable = true)
    private Integer note;
    @Basic
    @Column(name = "status", nullable = false, length = 255)
    private String status;

}
