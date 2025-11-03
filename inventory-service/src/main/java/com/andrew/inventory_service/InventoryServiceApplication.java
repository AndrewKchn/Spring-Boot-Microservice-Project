package com.andrew.inventory_service;

import com.andrew.inventory_service.model.Inventory;
import com.andrew.inventory_service.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
//        return args -> {
//            Inventory inventory1 = new Inventory();
//            inventory1.setSkuCode("Samsung A16");
//            inventory1.setQuantity(100);
//
//            Inventory inventory2 = new Inventory();
//            inventory2.setSkuCode("Samsung A54");
//            inventory2.setQuantity(0);
//
//            inventoryRepository.save(inventory1);
//            inventoryRepository.save(inventory2);
//        };
//    }
}
