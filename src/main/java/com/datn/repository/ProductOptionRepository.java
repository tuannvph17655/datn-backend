package com.datn.repository;

import com.datn.dto.customer.product.ColorResponse;
import com.datn.dto.customer.size.response.SizeResponse;
import com.datn.entity.ProductOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOptionRepository extends JpaRepository<ProductOptionEntity, String> {

    @Query("SELECT o FROM ProductOptionEntity o WHERE o.productId = :id")
    List<ProductOptionEntity> findByProductId(@Param("id") String id);

    List<ProductOptionEntity> findByProductIdAndActive(String productId, Boolean active);

    @Query(value = "SELECT * FROM product_option po WHERE po.size_id = ?1 AND po.color_id = ?2 AND po.product_id = ?3", nativeQuery = true)
    ProductOptionEntity findBySizeAndColorId(String sizeId, String color, String productId);

    @Query("select DISTINCT new com.datn.dto.customer.product.ColorResponse(" +
            "c.id,\n" +
            "c.name)\n" +
            "from ProductOptionEntity po\n" +
            "JOIN ColorEntity c on po.colorId = c.id\n" +
            "where po.sizeId = :sizeId and po.productId = :productId")
    List<ColorResponse> getListColorNameBySize(@Param("sizeId") String sizeId, @Param("productId") String productId);

    @Query("SELECT distinct po.image\n" +
            "from ProductOptionEntity po\n" +
            "where po.productId = ?1")
    List<String> findImageByProductId(String productId);

    @Query("select DISTINCT new com.datn.dto.customer.size.response.SizeResponse(" +
            "s.id,\n" +
            "s.name)\n" +
            "from ProductOptionEntity po\n" +
            "JOIN SizeEntity s on po.sizeId = s.id\n" +
            "where po.productId = :productId")
    List<SizeResponse> findListSizeByProductId(@Param("productId") String productId);


}
