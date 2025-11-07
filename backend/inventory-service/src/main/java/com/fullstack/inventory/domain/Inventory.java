package com.fullstack.inventory.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "inventory")
public class Inventory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "product_id", nullable = false, unique = true)
    private Long productId;
    
    @Column(name = "product_name", nullable = false)
    private String productName;
    
    @Column(name = "sku", nullable = false, unique = true)
    private String sku;
    
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    
    @Column(name = "reserved_quantity", nullable = false)
    private Integer reservedQuantity;
    
    @Column(name = "available_quantity", nullable = false)
    private Integer availableQuantity;
    
    @Column(name = "min_stock_level")
    private Integer minStockLevel;
    
    @Column(name = "max_stock_level")
    private Integer maxStockLevel;
    
    // Constructores
    public Inventory() {
        this.quantity = 0;
        this.reservedQuantity = 0;
        this.availableQuantity = 0;
        this.minStockLevel = 5;
        this.maxStockLevel = 100;
    }
    
    public Inventory(Long productId, String productName, String sku, Integer quantity) {
        this();
        this.productId = productId;
        this.productName = productName;
        this.sku = sku;
        this.quantity = quantity;
        this.availableQuantity = quantity;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    
    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }
    
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { 
        this.quantity = quantity; 
        updateAvailableQuantity();
    }
    
    public Integer getReservedQuantity() { return reservedQuantity; }
    public void setReservedQuantity(Integer reservedQuantity) { 
        this.reservedQuantity = reservedQuantity; 
        updateAvailableQuantity();
    }
    
    public Integer getAvailableQuantity() { return availableQuantity; }
    
    public Integer getMinStockLevel() { return minStockLevel; }
    public void setMinStockLevel(Integer minStockLevel) { this.minStockLevel = minStockLevel; }
    
    public Integer getMaxStockLevel() { return maxStockLevel; }
    public void setMaxStockLevel(Integer maxStockLevel) { this.maxStockLevel = maxStockLevel; }
    
    // Métodos de negocio
    private void updateAvailableQuantity() {
        this.availableQuantity = this.quantity - this.reservedQuantity;
    }
    
    public boolean reserveStock(Integer quantity) {
        if (this.availableQuantity >= quantity) {
            this.reservedQuantity += quantity;
            updateAvailableQuantity();
            return true;
        }
        return false;
    }
    
    public boolean releaseStock(Integer quantity) {
        if (this.reservedQuantity >= quantity) {
            this.reservedQuantity -= quantity;
            updateAvailableQuantity();
            return true;
        }
        return false;
    }
    
    public boolean commitStock(Integer quantity) {
        if (this.reservedQuantity >= quantity) {
            this.quantity -= quantity;
            this.reservedQuantity -= quantity;
            updateAvailableQuantity();
            return true;
        }
        return false;
    }
    
    public boolean isLowStock() {
        return this.availableQuantity <= this.minStockLevel;
    }
    
    public boolean isOutOfStock() {
        return this.availableQuantity <= 0;
    }
    
    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", sku='" + sku + '\'' +
                ", quantity=" + quantity +
                ", reservedQuantity=" + reservedQuantity +
                ", availableQuantity=" + availableQuantity +
                '}';
    }
}