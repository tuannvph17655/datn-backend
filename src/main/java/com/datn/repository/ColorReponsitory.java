package com.datn.repository;

import com.datn.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColorReponsitory extends JpaRepository<Color, Long> {
}

