package com.datn.repository;

import com.datn.dto.customer.product.ProductRelatedRes;
import com.datn.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    List<ProductEntity> findByCategoryIdAndActive(String productId, Boolean active);

//    @Query(value = "select distinct on (p.id) p.id AS productId,p.name AS productName,po.price AS price,po.image AS image \n" +
//            "from product p \n" +
//            "join category c on p.category_id = c.id \n" +
//            "join product_option po on p.id = po.product_id \n" +
//            "where p.category_id = :categoryId",nativeQuery = true)
//    List<ProductRelatedRes> getProductRelated(String categoryId);

    @Query("select p from ProductEntity p where p.active = true order by p.name")
    List<ProductEntity> findAllOrderByName();

    @Query("select count(p) from ProductEntity p where p.categoryId = ?1")
    Long countByCategoryId(String categoryId);

    List<ProductEntity> findAllByCategoryId(String categoryId);

    @Query("select count(od.productOptionId)\n" +
            "from OrderDetailEntity od\n" +
            "left join OrderEntity o on o.id = od.orderId and o.payed = true\n" +
            "left join ProductOptionEntity po on po.id = od.productOptionId\n" +
            "left join ProductEntity p on p.id = po.productId and p.active = true\n" +
            "where p.id = :id\n" +
            "group by p.id")
    Long countSellNumber(@Param("id") String id);
}
