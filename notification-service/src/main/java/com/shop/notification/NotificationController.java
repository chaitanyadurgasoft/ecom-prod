package com.shop.notification;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*")
public class NotificationController {
    
    // POST - Send order notification
    @PostMapping("/order-created")
    public String sendOrderNotification(@RequestBody Object order) {
        System.out.println("📧 Sending email notification for new order...");
        System.out.println("✅ Email sent successfully!");
        return "Notification sent!";
    }
    
    // POST - Send custom notification
    @PostMapping("/send")
    public String sendNotification(@RequestBody NotificationRequest request) {
        System.out.println("📧 Sending notification:");
        System.out.println("   To: " + request.getTo());
        System.out.println("   Message: " + request.getMessage());
        System.out.println("✅ Notification sent!");
        return "Notification sent to " + request.getTo();
    }
    
    // Health check
    @GetMapping("/health")
    public String health() {
        return "✅ Notification Service is running!";
    }
}

class NotificationRequest {
    private String to;
    private String message;
    
    public String getTo() { return to; }
    public void setTo(String to) { this.to = to; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
