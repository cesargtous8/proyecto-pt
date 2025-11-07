package com.fullstack.inventory.config;

import com.fullstack.inventory.domain.Inventory;
import com.fullstack.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public void run(String... args) throws Exception {
        // Limpiar datos existentes
        inventoryRepository.deleteAll();
        
        // Crear inventario para los productos (IDs deben coincidir con Product Service)
        inventoryRepository.save(new Inventory(1L, "Laptop Gaming", "LAP-GAM-001", 15));
        inventoryRepository.save(new Inventory(2L, "Smartphone Pro", "PHN-PRO-002", 8));
        inventoryRepository.save(new Inventory(3L, "Wireless Headphones", "AUD-HP-003", 25));
        inventoryRepository.save(new Inventory(4L, "Office Chair", "FUR-CH-004", 12));
        inventoryRepository.save(new Inventory(5L, "Desk Lamp", "HOME-LP-005", 50));
        inventoryRepository.save(new Inventory(6L, "Mechanical Keyboard", "KEY-MEC-006", 30));
        inventoryRepository.save(new Inventory(7L, "Gaming Mouse", "MOU-GAM-007", 20));
        
        System.out.println("Inventory data loaded successfully! Total items: " + inventoryRepository.count());
        
        // Mostrar datos cargados
        inventoryRepository.findAll().forEach(inv -> {
            System.out.println("Inventory: " + inv.getProductName() + " - Stock: " + inv.getAvailableQuantity());
        });
    }
}