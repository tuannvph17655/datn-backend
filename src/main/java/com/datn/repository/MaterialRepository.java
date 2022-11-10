package com.datn.repository;

import com.datn.dto.admin.material.MaterialRes;
import com.datn.entity.MaterialEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author myname
 */
@Repository
public interface MaterialRepository extends JpaRepository<MaterialEntity, String> {
    Boolean existsByIdAndActive(String id, Boolean active);
    MaterialEntity findByIdAndActive(String id, Boolean active);

    @Query("select new com.datn.dto.admin.material.MaterialRes(o.id, o.name, o.active,o.code) from MaterialEntity o")
    Page<MaterialRes> findAllMaterial(Pageable pageable);
}
