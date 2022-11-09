package com.datn.repository;

import com.datn.dto.customer.discount.DiscountResponse;
import com.datn.entity.DiscountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DiscountRepository extends JpaRepository<DiscountEntity ,String> {
    @Query("select d from DiscountEntity d where upper(d.code) = upper(?1) and d.deleted = false and d.status = 'ACTIVE'")
   DiscountEntity findByCode(String discountCode);

    @Query("select DISTINCT new com.datn.dto.customer.discount.DiscountResponse(" +
            "d.id,\n" +
            "d.des,\n" +
            "d.code,\n" +
            "d.startDate,\n" +
            "d.endDate,\n" +
            "d.prerequisiteValue,\n" +
            "d.percentDiscount,\n" +
            "d.status,\n" +
            "d.deleted,\n" +
            "d.eventId)\n" +
            "from DiscountEntity d")
    List<DiscountResponse> findAllDiscount();

}
