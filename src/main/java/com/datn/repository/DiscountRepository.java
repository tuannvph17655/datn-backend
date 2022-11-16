package com.datn.repository;

import com.datn.dto.customer.discount.DiscountResponse;
import com.datn.entity.VoucherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiscountRepository extends JpaRepository<VoucherEntity,String> {
    @Query("select d from VoucherEntity d where upper(d.code) = upper(?1) and d.deleted = false and d.status = 'ACTIVE'")
    VoucherEntity findByCode(String discountCode);

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
            "from VoucherEntity d")
    List<DiscountResponse> findAllDiscount();

}
