package com.datn.repository;

import com.datn.dto.customer.order.order_detail.ProductOrderDetail;
import com.datn.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity ,String> {

    @Query("select DISTINCT new com.datn.dto.customer.order.order_detail.ProductOrderDetail(" +
            "od.productOptionId,\n" +
            "od.qty)\n" +
            "from OrderDetailEntity od\n" +
            "LEFT JOIN OrderEntity o on od.orderId = o.id\n" +
            "where od.orderId = :orderId")
    List<ProductOrderDetail> getProductOrder(@Param("orderId") String orderId);
}
