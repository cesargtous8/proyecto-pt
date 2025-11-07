package com.fullstack.inventory.service;

import com.fullstack.inventory.domain.Inventory;
import com.fullstack.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;
    
    @Autowired
    private RestTemplate restTemplate;
    
    private final String PRODUCT_SERVICE_URL = "http://localhost:8081/api/products";

    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    public Optional<Inventory> getInventoryByProductId(Long productId) {
        return inventoryRepository.findByProductId(productId);
    }

    public Optional<Inventory> getInventoryBySku(String sku) {
        return inventoryRepository.findBySku(sku);
    }

    @Transactional
    public Inventory createInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Transactional
    public boolean processPurchase(Long productId, Integer quantity) {
        Optional<Inventory> inventoryOpt = inventoryRepository.findByProductId(productId);
        if (inventoryOpt.isPresent()) {
            Inventory inventory = inventoryOpt.get();
            
            // Verificar stock disponible
            if (inventory.getAvailableQuantity() < quantity) {
                throw new IllegalArgumentException("Insufficient stock. Available: " + inventory.getAvailableQuantity() + ", Requested: " + quantity);
            }
            
            // Reservar y comprometer el stock
            boolean reserved = inventory.reserveStock(quantity);
            if (reserved) {
                boolean committed = inventory.commitStock(quantity);
                if (committed) {
                    inventoryRepository.save(inventory);
                    return true;
                }
            }
        }
        return false;
    }

    @Transactional
    public boolean reserveStock(Long productId, Integer quantity) {
        Optional<Inventory> inventoryOpt = inventoryRepository.findByProductId(productId);
        if (inventoryOpt.isPresent()) {
            Inventory inventory = inventoryOpt.get();
            boolean reserved = inventory.reserveStock(quantity);
            if (reserved) {
                inventoryRepository.save(inventory);
                return true;
            }
        }
        return false;
    }

    @Transactional
    public boolean commitStock(Long productId, Integer quantity) {
        Optional<Inventory> inventoryOpt = inventoryRepository.findByProductId(productId);
        if (inventoryOpt.isPresent()) {
            Inventory inventory = inventoryOpt.get();
            boolean committed = inventory.commitStock(quantity);
            if (committed) {
                inventoryRepository.save(inventory);
                return true;
            }
        }
        return false;
    }

    @Transactional
    public boolean releaseStock(Long productId, Integer quantity) {
        Optional<Inventory> inventoryOpt = inventoryRepository.findByProductId(productId);
        if (inventoryOpt.isPresent()) {
            Inventory inventory = inventoryOpt.get();
            boolean released = inventory.releaseStock(quantity);
            if (released) {
                inventoryRepository.save(inventory);
                return true;
            }
        }
        return false;
    }

    @Transactional
    public Inventory updateStock(Long productId, Integer newQuantity) {
        Optional<Inventory> inventoryOpt = inventoryRepository.findByProductId(productId);
        if (inventoryOpt.isPresent()) {
            Inventory inventory = inventoryOpt.get();
            inventory.setQuantity(newQuantity);
            return inventoryRepository.save(inventory);
        }
        throw new RuntimeException("Inventory not found for product id: " + productId);
    }

    public List<Inventory> getLowStockItems() {
        return inventoryRepository.findByAvailableQuantityLessThanEqual(5);
    }

    public List<Inventory> getOutOfStockItems() {
        return inventoryRepository.findByAvailableQuantity(0);
    }

    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }
    
    @Transactional
    public void deleteInventoryByProductId(Long productId) {
        inventoryRepository.deleteByProductId(productId);
    }
}