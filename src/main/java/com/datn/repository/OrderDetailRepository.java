package com.datn.repository;

import com.datn.dto.customer.order.OrderDetailRes;
import com.datn.dto.customer.order.ProductInOrderDetail;
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


    @Query("select DISTINCT new com.datn.dto.customer.order.OrderDetailRes(" +
            "a.nameOfRecipient,\n" +
            "a.phoneNumber,\n" +
            "o.note,\n" +
            "o.code,\n" +
            "o.createdDate,\n" +
            "o.total,\n" +
            "o.shipPrice,\n" +
            "trim(concat(coalesce(a.addressDetail, ''), ', ', coalesce(a.wardName, ''),', ', coalesce(a.districtName, ''),', ', coalesce(a.provinceName, ''))),\n" +
            "o.payment,\n" +
            "o.payed,\n" +
            "o.status)\n" +
            "from OrderDetailEntity od\n" +
            "LEFT JOIN OrderEntity o on od.orderId = o.id\n" +
            "LEFT JOIN AddressEntity a on o.addressId = a.id\n" +
            "where od.orderId = :orderId")
    OrderDetailRes getOrderDetail(@Param("orderId") String orderId);

    @Query("select new com.datn.dto.customer.order.ProductInOrderDetail(\n" +
            "p.name,\n" +
            "po.id,\n" +
            "p.id,\n" +
            "po.image,\n" +
            "s.name,\n" +
            "c.name,\n" +
            "od.price,\n" +
            "od.qty,\n" +
            "od.qty * od.price)\n" +
            "from OrderDetailEntity od\n" +
            "left join ProductOptionEntity po on po.id = od.productOptionId\n" +
            "left join ProductEntity p on p.id = po.productId\n" +
            "left join ColorEntity c on c.id = po.colorId\n" +
            "left join SizeEntity s on s.id = po.sizeId\n" +
            "where od.orderId = :orderId")
    List<ProductInOrderDetail> getProductList(@Param("orderId") String orderId);

}
