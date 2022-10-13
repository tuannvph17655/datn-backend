package com.datn.repository;

import com.datn.dto.customer.cart.response.CartResponse;
import com.datn.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, String> {

    Optional<CartEntity> findByUserIdAndAndProductOptionId(String userId, String productOptionId);

    @Query("SELECT new com.datn.dto.customer.cart.response.CartResponse(" +
            "c.id, p.id, po.id, p.name, s.name, cl.name, c.quantity, po.qty, po.price, po.image, (po.price * c.quantity), ct.id)\n" +
            "from CartEntity c\n" +
            "JOIN ProductOptionEntity po ON c.productOptionId = po.id\n" +
            "JOIN SizeEntity s ON po.sizeId = s.id\n" +
            "JOIN ColorEntity cl ON po.colorId = cl.id\n" +
            "JOIN ProductEntity p ON po.productId = p.id\n" +
            "JOIN CategoryEntity ct on ct.id = p.categoryId\n" +
            "WHERE c.userId = :userId")
    List<CartResponse> getListCart(@Param("userId") String userId);

    @Modifying
    @Query("DELETE FROM CartEntity c WHERE c.productOptionId = :productOptionId")
    void deleteByProductOptionId(@Param("productOptionId") String productOptionId);

    @Query("SELECT COUNT(c.productOptionId) FROM CartEntity c WHERE c.userId = :userId")
    Integer countItemInCart(@Param("userId") String userId);

    @Modifying
    @Query(value = "DELETE FROM cart  WHERE user_id = :userId ", nativeQuery = true)
    void clearCart(String userId);

}
