package com.fullstack.product.repository;

import com.fullstack.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    Optional<Product> findBySku(String sku);
    
    List<Product> findByNameContainingIgnoreCase(String name);
    
    List<Product> findByCategoryIgnoreCase(String category);
    
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
    
    // Métodos para gestión de stock
    List<Product> findByStockQuantityLessThanEqual(Integer threshold);
    
    List<Product> findByStockQuantity(Integer quantity);
    
    List<Product> findByStockQuantityGreaterThan(Integer quantity);
    
    @Query("SELECT p FROM Product p WHERE p.stockQuantity > 0 AND p.stockQuantity <= :threshold")
    List<Product> findLowStockProducts(@Param("threshold") Integer threshold);
    
    @Query("SELECT p FROM Product p WHERE p.stockQuantity = 0")
    List<Product> findOutOfStockProducts();
    
    @Query("SELECT p FROM Product p WHERE p.stockQuantity > 0")
    List<Product> findInStockProducts();
    
    boolean existsBySku(String sku);
    
    long countByCategory(String category);
}