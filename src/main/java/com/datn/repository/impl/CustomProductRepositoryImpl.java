package com.datn.repository.impl;

import com.datn.repository.CustomProductRepository;
import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;

@AllArgsConstructor
public class CustomProductRepositoryImpl implements CustomProductRepository {
    private final EntityManager entityManager;

}