package com.fullstack.inventory.service;

import com.fullstack.inventory.domain.Inventory;
import com.fullstack.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class InventoryService {
    
    @Autowired
    private InventoryRepository inventoryRepository;
    
    public Optional<Inventory> getInventoryByProductId(String productId) {
        return inventoryRepository.findByProductId(productId);
    }
    
    public Inventory createOrUpdateInventory(String productId, Integer quantity) {
        return inventoryRepository.findByProductId(productId)
            .map(inventory -> {
                inventory.setQuantity(quantity);
                return inventoryRepository.save(inventory);
            })
            .orElseGet(() -> {
                Inventory newInventory = new Inventory(productId, quantity);
                return inventoryRepository.save(newInventory);
            });
    }
    
    public boolean initializeInventory(String productId) {
        if (!inventoryRepository.existsByProductId(productId)) {
            Inventory inventory = new Inventory(productId, 0);
            inventoryRepository.save(inventory);
            return true;
        }
        return false;
    }
}