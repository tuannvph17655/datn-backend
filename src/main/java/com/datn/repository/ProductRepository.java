package com.datn.repository;

import com.datn.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface ProductRepository extends JpaRepository<Product, Long>, CustomProductRepository {

}
