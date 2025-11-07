package com.fullstack.product.service;

import com.fullstack.product.domain.Product;
import com.fullstack.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        // Limpiar datos existentes
        productRepository.deleteAll();
        
        // Insertar productos de prueba con stock real
        productRepository.save(new Product("Laptop Gaming", 
            "High-performance gaming laptop with RTX 4070", "Electronics", 1299.99, "LAP-GAM-001", 8));
        productRepository.save(new Product("Smartphone Pro", 
            "Latest smartphone with advanced camera system", "Electronics", 899.99, "PHN-PRO-002", 15));
        productRepository.save(new Product("Wireless Headphones", 
            "Noise-cancelling wireless headphones with 30h battery", "Audio", 199.99, "AUD-HP-003", 25));
        productRepository.save(new Product("Office Chair", 
            "Ergonomic office chair with lumbar support", "Furniture", 299.99, "FUR-CH-004", 12));
        productRepository.save(new Product("Desk Lamp", 
            "LED desk lamp with adjustable brightness and color temperature", "Home", 49.99, "HOME-LP-005", 50));
        productRepository.save(new Product("Mechanical Keyboard", 
            "RGB mechanical keyboard with blue switches", "Electronics", 89.99, "KEY-MEC-006", 30));
        productRepository.save(new Product("Gaming Mouse", 
            "High-precision gaming mouse with customizable weights", "Electronics", 59.99, "MOU-GAM-007", 20));
        
        System.out.println("Sample products loaded successfully! Total products: " + productRepository.count());
    }
}