package com.shop.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderServiceApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
        System.out.println("\n🎉 Order Service Started!");
        System.out.println("📍 Running on: http://localhost:8082");
        System.out.println("🔗 API: http://localhost:8082/api/orders\n");
    }
}
