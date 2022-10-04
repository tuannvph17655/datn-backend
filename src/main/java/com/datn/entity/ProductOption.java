package com.datn.entity;

import javax.persistence.*;

@Entity
@Table(name = "product_options")
public class ProductOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private Long colorId;

    private Long sizeId;

    private Integer quantity;

    private String image;

    private Boolean active;
}
