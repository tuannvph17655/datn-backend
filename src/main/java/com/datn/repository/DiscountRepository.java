package com.datn.repository;

import com.datn.entity.DiscountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DiscountRepository extends JpaRepository<DiscountEntity ,String> {
    @Query("select d from DiscountEntity d where upper(d.code) = upper(?1) and d.deleted = false and d.status = 'ACTIVE'")
    DiscountEntity findByCode(String discountCode);
}
