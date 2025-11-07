package com.fullstack.inventory.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "http://localhost:8081")
public interface ProductServiceClient {
    
    @GetMapping("/api/products/{productCode}")
    Object getProduct(@PathVariable("productCode") String productCode);
    
    @GetMapping("/api/products/{productCode}/exists")
    Boolean productExists(@PathVariable("productCode") String productCode);
}