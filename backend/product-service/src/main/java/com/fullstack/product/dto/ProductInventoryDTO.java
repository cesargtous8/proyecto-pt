package com.fullstack.product.dto;

import com.fullstack.product.domain.Product;

public class ProductInventoryDTO {
    private Product product;
    private Integer availableQuantity;
    private Boolean inStock;
    private Boolean lowStock;
    
    public ProductInventoryDTO(Product product, Integer availableQuantity) {
        this.product = product;
        this.availableQuantity = availableQuantity;
        this.inStock = availableQuantity > 0;
        this.lowStock = availableQuantity <= 5;
    }
    
    // Getters y Setters
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    
    public Integer getAvailableQuantity() { return availableQuantity; }
    public void setAvailableQuantity(Integer availableQuantity) { this.availableQuantity = availableQuantity; }
    
    public Boolean getInStock() { return inStock; }
    public void setInStock(Boolean inStock) { this.inStock = inStock; }
    
    public Boolean getLowStock() { return lowStock; }
    public void setLowStock(Boolean lowStock) { this.lowStock = lowStock; }
}