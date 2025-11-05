package com.fullstack.inventory.config;

import com.fullstack.inventory.domain.Inventory;
import com.fullstack.inventory.repository.InventoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);
    
    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public void run(String... args) throws Exception {
        // Limpiar datos existentes
        inventoryRepository.deleteAll();

        // Crear inventarios de prueba para los productos existentes
        Inventory inv1 = new Inventory("prod-001", 10);
        Inventory inv2 = new Inventory("prod-002", 25);
        Inventory inv3 = new Inventory("prod-003", 15);

        inventoryRepository.save(inv1);
        inventoryRepository.save(inv2);
        inventoryRepository.save(inv3);

        logger.info("✅ Inventarios de prueba cargados - {} registros", inventoryRepository.count());
        logger.info("📦 Inventario 1: Producto {}, Cantidad: {}", inv1.getProductId(), inv1.getQuantity());
        logger.info("📦 Inventario 2: Producto {}, Cantidad: {}", inv2.getProductId(), inv2.getQuantity());
        logger.info("📦 Inventario 3: Producto {}, Cantidad: {}", inv3.getProductId(), inv3.getQuantity());
    }
}