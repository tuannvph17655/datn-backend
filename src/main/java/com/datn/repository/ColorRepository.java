package com.datn.repository;

import com.datn.entity.ColorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author myname
 */
@Repository
public interface ColorRepository extends JpaRepository<ColorEntity, String> {

    @Query("select distinct c.name \n" +
            "from ColorEntity c\n" +
            "left join ProductOptionEntity po on po.colorId = c.id\n" +
            "where po.productId = ?1")
    List<String> findDistinctByProductId(String productId);

}
