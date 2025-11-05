package com.fullstack.inventory.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "http://localhost:8081")
public interface ProductServiceClient {
    
    @GetMapping("/api/products/{productId}/exists")
    Boolean checkProductExists(@PathVariable String productId);
    
    @GetMapping("/api/products/{productId}")
    Object getProduct(@PathVariable String productId);
}