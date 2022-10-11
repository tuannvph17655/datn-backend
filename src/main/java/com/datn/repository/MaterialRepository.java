package com.datn.repository;

import com.datn.entity.MaterialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author myname
 */
@Repository
public interface MaterialRepository extends JpaRepository<MaterialEntity, String> {

}
