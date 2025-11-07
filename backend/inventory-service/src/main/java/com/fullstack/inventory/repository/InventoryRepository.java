package com.fullstack.inventory.repository;

import com.fullstack.inventory.domain.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    
    Optional<Inventory> findByProductId(Long productId);
    
    Optional<Inventory> findBySku(String sku);
    
    List<Inventory> findByAvailableQuantityLessThanEqual(Integer quantity);
    
    List<Inventory> findByAvailableQuantity(Integer quantity);
    
    boolean existsByProductId(Long productId);
    
    boolean existsBySku(String sku);
    
    @Modifying
    @Query("DELETE FROM Inventory i WHERE i.productId = :productId")
    void deleteByProductId(@Param("productId") Long productId);
    
    @Query("SELECT i FROM Inventory i WHERE i.availableQuantity <= i.minStockLevel AND i.availableQuantity > 0")
    List<Inventory> findLowStockItems();
    
    @Query("SELECT i FROM Inventory i WHERE i.availableQuantity = 0")
    List<Inventory> findOutOfStockItems();
}