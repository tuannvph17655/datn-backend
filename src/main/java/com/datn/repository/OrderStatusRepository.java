package com.datn.repository;

import com.datn.entity.OrderEntity;
import com.datn.entity.OrderStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatusEntity, String> {
}
