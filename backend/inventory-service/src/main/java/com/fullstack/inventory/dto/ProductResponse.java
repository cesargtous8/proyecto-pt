package com.fullstack.inventory.dto;

import java.math.BigDecimal;

public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private String createdAt;
    private String updatedAt;
    
    // Getters and Setters
    public String getId() { 
        return id; 
    }
    
    public void setId(String id) { 
        this.id = id; 
    }
    
    public String getName() { 
        return name; 
    }
    
    public void setName(String name) { 
        this.name = name; 
    }
    
    public String getDescription() { 
        return description; 
    }
    
    public void setDescription(String description) { 
        this.description = description; 
    }
    
    public BigDecimal getPrice() { 
        return price; 
    }
    
    public void setPrice(BigDecimal price) { 
        this.price = price; 
    }
    
    public String getCreatedAt() { 
        return createdAt; 
    }
    
    public void setCreatedAt(String createdAt) { 
        this.createdAt = createdAt; 
    }
    
    public String getUpdatedAt() { 
        return updatedAt; 
    }
    
    public void setUpdatedAt(String updatedAt) { 
        this.updatedAt = updatedAt; 
    }
    
    @Override
    public String toString() {
        return "ProductResponse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}