package com.fullstack.inventory.controller;

import com.fullstack.inventory.domain.Inventory;
import com.fullstack.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    
    @Autowired
    private InventoryService inventoryService;
    
    @GetMapping
    public List<Inventory> getAllInventory() {
        return inventoryService.getAllInventory();
    }
    
    @GetMapping("/product/{productId}")
    public ResponseEntity<Inventory> getInventoryByProductId(@PathVariable Long productId) {
        Optional<Inventory> inventory = inventoryService.getInventoryByProductId(productId);
        return inventory.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/sku/{sku}")
    public ResponseEntity<Inventory> getInventoryBySku(@PathVariable String sku) {
        Optional<Inventory> inventory = inventoryService.getInventoryBySku(sku);
        return inventory.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory) {
        Inventory savedInventory = inventoryService.createInventory(inventory);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedInventory);
    }
    
    @PostMapping("/purchase")
    public ResponseEntity<?> processPurchase(@RequestParam Long productId, @RequestParam Integer quantity) {
        try {
            boolean processed = inventoryService.processPurchase(productId, quantity);
            return processed ? ResponseEntity.ok(true) : ResponseEntity.badRequest().body(false);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing purchase");
        }
    }
    
    @PutMapping("/product/{productId}/reserve")
    public ResponseEntity<Boolean> reserveStock(@PathVariable Long productId, @RequestParam Integer quantity) {
        boolean reserved = inventoryService.reserveStock(productId, quantity);
        return reserved ? ResponseEntity.ok(true) : ResponseEntity.badRequest().body(false);
    }
    
    @PutMapping("/product/{productId}/commit")
    public ResponseEntity<Boolean> commitStock(@PathVariable Long productId, @RequestParam Integer quantity) {
        boolean committed = inventoryService.commitStock(productId, quantity);
        return committed ? ResponseEntity.ok(true) : ResponseEntity.badRequest().body(false);
    }
    
    @PutMapping("/product/{productId}/release")
    public ResponseEntity<Boolean> releaseStock(@PathVariable Long productId, @RequestParam Integer quantity) {
        boolean released = inventoryService.releaseStock(productId, quantity);
        return released ? ResponseEntity.ok(true) : ResponseEntity.badRequest().body(false);
    }
    
    @PutMapping("/product/{productId}/stock")
    public ResponseEntity<Inventory> updateStock(@PathVariable Long productId, @RequestParam Integer quantity) {
        try {
            Inventory updatedInventory = inventoryService.updateStock(productId, quantity);
            return ResponseEntity.ok(updatedInventory);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/low-stock")
    public List<Inventory> getLowStockItems() {
        return inventoryService.getLowStockItems();
    }
    
    @GetMapping("/out-of-stock")
    public List<Inventory> getOutOfStockItems() {
        return inventoryService.getOutOfStockItems();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.ok().build();
    }
}