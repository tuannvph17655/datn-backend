package com.datn.repository;

import com.datn.dto.admin.size.SizeRes;
import com.datn.dto.customer.size.response.SizeResponse;
import com.datn.entity.SizeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SizeRepository extends JpaRepository<SizeEntity, String> {
    @Query("select distinct s.name\n" +
            "from SizeEntity s\n" +
            "left join ProductOptionEntity po on po.sizeId = s.id\n" +
            "where po.productId = ?1")
    List<String> findDistinctByProductId(String productId);

    @Query("select DISTINCT new com.datn.dto.customer.size.response.SizeResponse(" +
            "s.id,\n" +
            "s.code)\n" +
            "from SizeEntity s")
    List<SizeResponse> getAllSize();

    @Query("select DISTINCT new com.datn.dto.admin.size.SizeRes(" +
            "s.id,\n" +
            "s.name,\n " +
            "s.code, s.active)\n" +
            "from SizeEntity s")
    Page<SizeRes> getAllSize(Pageable pageable);

    @Query("select distinct s.name\n" +
            "from SizeEntity s\n" +
            "left join ProductOptionEntity po on po.sizeId = s.id\n" +
            "where po.productId = ?1")
    List<String> findByProductId(String productId);

    String findNameById(String sizeId);

}
