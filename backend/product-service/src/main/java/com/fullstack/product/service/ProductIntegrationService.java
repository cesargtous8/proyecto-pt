package com.fullstack.product.service;

import com.fullstack.product.domain.Product;
import com.fullstack.product.dto.ProductInventoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductIntegrationService {

    @Autowired
    private ProductService productService;
    
    @Autowired
    private RestTemplate restTemplate;
    
    private final String INVENTORY_SERVICE_URL = "http://localhost:8080/api/inventory";
    
    public List<ProductInventoryDTO> getAllProductsWithInventory() {
        List<Product> products = productService.getAllProducts();
        List<ProductInventoryDTO> result = new ArrayList<>();
        
        for (Product product : products) {
            Integer quantity = getAvailableQuantity(product.getId());
            result.add(new ProductInventoryDTO(product, quantity));
        }
        
        return result;
    }
    
    public Optional<ProductInventoryDTO> getProductWithInventory(Long productId) {
        Optional<Product> product = productService.getProductById(productId);
        if (product.isPresent()) {
            Integer quantity = getAvailableQuantity(productId);
            return Optional.of(new ProductInventoryDTO(product.get(), quantity));
        }
        return Optional.empty();
    }
    
    private Integer getAvailableQuantity(Long productId) {
        try {
            String url = INVENTORY_SERVICE_URL + "/product/" + productId;
            ResponseEntity<InventoryResponse> response = restTemplate.getForEntity(url, InventoryResponse.class);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody().getAvailableQuantity();
            }
        } catch (Exception e) {
            System.err.println("Error fetching inventory for product " + productId + ": " + e.getMessage());
        }
        return 0;
    }
    
    public static class InventoryResponse {
        private Integer availableQuantity;
        
        public Integer getAvailableQuantity() { 
            return availableQuantity; 
        }
        
        public void setAvailableQuantity(Integer availableQuantity) { 
            this.availableQuantity = availableQuantity; 
        }
    }
}