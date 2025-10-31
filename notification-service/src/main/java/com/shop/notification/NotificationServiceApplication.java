package com.shop.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotificationServiceApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
        System.out.println("\n🎉 Notification Service Started!");
        System.out.println("📍 Running on: http://localhost:8084");
        System.out.println("🔗 API: http://localhost:8084/api/notifications\n");
    }
}
