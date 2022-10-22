package com.datn.repository;

import com.datn.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String> {
    @Query(value = "SELECT * FROM orders o WHERE o.user_id = ?1 ORDER BY o.created_date desc",nativeQuery = true)
    List<OrderEntity> getMyOrder(String userId);
}
