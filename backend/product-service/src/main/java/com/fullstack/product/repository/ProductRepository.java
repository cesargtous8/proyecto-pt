package com.fullstack.product.repository;

import com.fullstack.product.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    
    Page<Product> findAll(Pageable pageable);
    
    boolean existsByName(String name);
}