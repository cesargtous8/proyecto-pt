package com.fullstack.product.controller;

import com.fullstack.product.domain.Product;
import com.fullstack.product.dto.ApiResponse;
import com.fullstack.product.dto.ProductRequest;
import com.fullstack.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Products", description = "Product Management API")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @PostMapping
    @Operation(summary = "Create a new product")
    public ResponseEntity<ApiResponse<Product>> createProduct(@Valid @RequestBody ProductRequest request) {
        try {
            Product product = productService.createProduct(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(product, "Product created successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Failed to create product: " + e.getMessage()));
        }
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID")
    public ResponseEntity<ApiResponse<Product>> getProduct(@PathVariable String id) {
        return productService.getProductById(id)
            .map(product -> ResponseEntity.ok(ApiResponse.success(product)))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("Product not found with id: " + id)));
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update product by ID")
    public ResponseEntity<ApiResponse<Product>> updateProduct(
            @PathVariable String id, 
            @Valid @RequestBody ProductRequest request) {
        try {
            return productService.updateProduct(id, request)
                .map(product -> ResponseEntity.ok(ApiResponse.success(product, "Product updated successfully")))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Product not found with id: " + id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Failed to update product: " + e.getMessage()));
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete product by ID")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable String id) {
        try {
            if (productService.deleteProduct(id)) {
                return ResponseEntity.ok(ApiResponse.success(null, "Product deleted successfully"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Product not found with id: " + id));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Failed to delete product: " + e.getMessage()));
        }
    }
    
    @GetMapping
    @Operation(summary = "Get all products with pagination")
    public ResponseEntity<ApiResponse<Page<Product>>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Product> products = productService.getAllProducts(pageable);
            return ResponseEntity.ok(ApiResponse.success(products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Failed to retrieve products: " + e.getMessage()));
        }
    }
}