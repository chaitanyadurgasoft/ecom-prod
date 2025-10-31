package com.shop.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
        System.out.println("\n🎉 Product Service Started!");
        System.out.println("📍 Running on: http://localhost:8081");
        System.out.println("🔗 API: http://localhost:8081/api/products\n");
    }
}
