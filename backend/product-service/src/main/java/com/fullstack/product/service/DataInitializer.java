package com.fullstack.product.service;

import com.fullstack.product.domain.Product;
import com.fullstack.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        // Limpiar y crear datos de prueba
        productRepository.deleteAll();

        Product product1 = new Product();
        product1.setId("prod-001");
        product1.setName("Laptop Gaming");
        product1.setDescription("High performance gaming laptop");
        product1.setPrice(new BigDecimal("1299.99"));

        Product product2 = new Product();
        product2.setId("prod-002");
        product2.setName("Wireless Mouse");
        product2.setDescription("Ergonomic wireless mouse");
        product2.setPrice(new BigDecimal("49.99"));

        Product product3 = new Product();
        product3.setId("prod-003");
        product3.setName("Mechanical Keyboard");
        product3.setDescription("RGB mechanical keyboard");
        product3.setPrice(new BigDecimal("89.99"));

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        System.out.println("✅ Datos de prueba cargados - " + productRepository.count() + " productos");
    }
}