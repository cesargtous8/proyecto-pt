package com.fullstack.product.service;

import com.fullstack.product.domain.Product;
import com.fullstack.product.dto.ProductRequest;
import com.fullstack.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    public Product createProduct(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        
        return productRepository.save(product);
    }
    
    @Transactional(readOnly = true)
    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }
    
    public Optional<Product> updateProduct(String id, ProductRequest request) {
        return productRepository.findById(id)
            .map(existingProduct -> {
                existingProduct.setName(request.getName());
                existingProduct.setDescription(request.getDescription());
                existingProduct.setPrice(request.getPrice());
                return productRepository.save(existingProduct);
            });
    }
    
    public boolean deleteProduct(String id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    @Transactional(readOnly = true)
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
}