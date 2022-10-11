package com.datn.repository;

import com.datn.entity.ProductOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOptionRepository extends JpaRepository<ProductOptionEntity, String> {
    List<ProductOptionEntity> findByProductIdAndActive(String productId, Boolean active);
}
