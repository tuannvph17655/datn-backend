package com.datn.repository;

import com.datn.entity.ProductEntity;
import com.datn.entity.SuggestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuggestRepository extends JpaRepository<SuggestEntity, String> {

    List<SuggestEntity> findAllSuggest(String suggestId);
}
