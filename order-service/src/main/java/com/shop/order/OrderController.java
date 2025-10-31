package com.shop.order;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {
    
    private List<Order> orders = new ArrayList<>();
    private RestTemplate restTemplate = new RestTemplate();
    
    // GET all orders
    @GetMapping
    public List<Order> getAllOrders() {
        System.out.println("📦 Getting all orders");
        return orders;
    }
    
    // GET orders by user
    @GetMapping("/user/{userId}")
    public List<Order> getUserOrders(@PathVariable Long userId) {
        System.out.println("👤 Getting orders for user: " + userId);
        return orders.stream()
            .filter(o -> o.getUserId().equals(userId))
            .toList();
    }
    
    // POST - Create new order
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        order.setId((long) (orders.size() + 1));
        order.setStatus("PENDING");
        
        // Calculate total
        double total = order.getItems().stream()
            .mapToDouble(item -> item.getPrice() * item.getQuantity())
            .sum();
        order.setTotal(total);
        
        orders.add(order);
        
        System.out.println("✅ Order created: #" + order.getId() + " - Total: $" + total);
        
        // Send notification (call notification service)
        try {
            restTemplate.postForObject(
                "http://localhost:8084/api/notifications/order-created",
                order,
                String.class
            );
        } catch (Exception e) {
            System.out.println("⚠️  Notification service not available");
        }
        
        return order;
    }
    
    // PUT - Update order status
    @PutMapping("/{id}/status")
    public Order updateStatus(@PathVariable Long id, @RequestBody String status) {
        Order order = orders.stream()
            .filter(o -> o.getId().equals(id))
            .findFirst()
            .orElse(null);
            
        if (order != null) {
            order.setStatus(status);
            System.out.println("📝 Order #" + id + " status updated to: " + status);
        }
        return order;
    }
    
    // Health check
    @GetMapping("/health")
    public String health() {
        return "✅ Order Service is running!";
    }
}
