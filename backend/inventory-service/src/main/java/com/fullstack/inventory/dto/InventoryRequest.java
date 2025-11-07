package com.fullstack.inventory.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class InventoryRequest {
    
    @NotNull(message = "Product code cannot be null")
    private String productCode;
    
    @Min(value = 0, message = "Quantity cannot be negative")
    private int quantity;
    
    @Min(value = 0, message = "Price cannot be negative")
    private double price;
    
    @Min(value = 0, message = "Reserved quantity cannot be negative")
    private int reservedQuantity;
    
    // Constructors
    public InventoryRequest() {}
    
    public InventoryRequest(String productCode, int quantity, double price, int reservedQuantity) {
        this.productCode = productCode;
        this.quantity = quantity;
        this.price = price;
        this.reservedQuantity = reservedQuantity;
    }
    
    // Getters and Setters
    public String getProductCode() {
        return productCode;
    }
    
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public int getReservedQuantity() {
        return reservedQuantity;
    }
    
    public void setReservedQuantity(int reservedQuantity) {
        this.reservedQuantity = reservedQuantity;
    }
}