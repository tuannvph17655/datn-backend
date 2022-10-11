package com.datn.repository;

import com.datn.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    @Query("select count(p) from ProductEntity p where p.categoryId = ?1")
    Long countByCategoryId(String categoryId);

    List<ProductEntity> findByCategoryIdAndActive(String productId, Boolean active);
}
