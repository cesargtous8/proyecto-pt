package com.fullstack.inventory.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class InventoryRequest {
    
    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity must be positive")
    private Integer quantity;
    
    @Min(value = 0, message = "Min stock must be positive")
    private Integer minStock;
    
    @Min(value = 1, message = "Max stock must be at least 1")
    private Integer maxStock;
    
    public InventoryRequest() {}
    
    public InventoryRequest(Integer quantity) {
        this.quantity = quantity;
    }
    
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    
    public Integer getMinStock() { return minStock; }
    public void setMinStock(Integer minStock) { this.minStock = minStock; }
    
    public Integer getMaxStock() { return maxStock; }
    public void setMaxStock(Integer maxStock) { this.maxStock = maxStock; }
}