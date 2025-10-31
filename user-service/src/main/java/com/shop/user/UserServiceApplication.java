package com.shop.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
        System.out.println("\n🎉 User Service Started!");
        System.out.println("📍 Running on: http://localhost:8083");
        System.out.println("🔗 API: http://localhost:8083/api/users\n");
    }
}
