package com.fullstack.inventory.controller;

import com.fullstack.inventory.domain.Inventory;
import com.fullstack.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    
    @Autowired
    private InventoryService inventoryService;
    
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Inventory Service is RUNNING with H2 Database!");
    }
    
    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getInventory(@PathVariable String productId) {
        return inventoryService.getInventoryByProductId(productId)
            .map(inventory -> ResponseEntity.ok(createSuccessResponse(inventory)))
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/product/{productId}")
    public ResponseEntity<?> createInventory(@PathVariable String productId, 
                                           @RequestParam Integer quantity) {
        Inventory inventory = inventoryService.createOrUpdateInventory(productId, quantity);
        return ResponseEntity.ok(createSuccessResponse(inventory));
    }
    
    @PostMapping("/product/{productId}/initialize")
    public ResponseEntity<?> initializeInventory(@PathVariable String productId) {
        boolean created = inventoryService.initializeInventory(productId);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", created ? "Inventory initialized" : "Inventory already exists");
        response.put("productId", productId);
        return ResponseEntity.ok(response);
    }
    
    private Map<String, Object> createSuccessResponse(Inventory inventory) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", inventory);
        return response;
    }
}