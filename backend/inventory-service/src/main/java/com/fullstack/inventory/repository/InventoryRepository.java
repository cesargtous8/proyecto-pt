package com.fullstack.inventory.repository;

import com.fullstack.inventory.domain.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByProductId(String productId);
    boolean existsByProductId(String productId);
}