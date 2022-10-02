package com.datn.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Basic
    @Column(name = "province_id", nullable = false)
    private int provinceId;
    @Basic
    @Column(name = "province_name", nullable = false, length = 255)
    private String provinceName;
    @Basic
    @Column(name = "distric_id", nullable = false)
    private int districId;
    @Basic
    @Column(name = "distric_name", nullable = false, length = 155)
    private String districName;
    @Basic
    @Column(name = "ward_code", nullable = false)
    private int wardCode;
    @Basic
    @Column(name = "ward_name", nullable = false, length = 155)
    private String wardName;
    @Basic
    @Column(name = "phone_number", nullable = false, length = 15)
    private String phoneNumber;
    @Basic
    @Column(name = "address_detail", nullable = true, length = 255)
    private String addressDetail;
    @Basic
    @Column(name = "is_default", nullable = true)
    private Boolean isDefault;
    @Basic
    @Column(name = "active", nullable = false)
    private boolean active;

}
