package com.shop.user;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    
    private List<User> users = new ArrayList<>();
    
    // Constructor - add demo user
    public UserController() {
        users.add(new User(1L, "John Doe", "john@example.com", "password123"));
    }
    
    // GET all users
    @GetMapping
    public List<User> getAllUsers() {
        System.out.println("👥 Getting all users");
        // Don't send passwords!
        users.forEach(u -> u.setPassword("***"));
        return users;
    }
    
    // POST - Register new user
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        // Check if email already exists
        boolean exists = users.stream()
            .anyMatch(u -> u.getEmail().equals(user.getEmail()));
            
        if (exists) {
            System.out.println("❌ Email already exists: " + user.getEmail());
            return null;
        }
        
        user.setId((long) (users.size() + 1));
        users.add(user);
        System.out.println("✅ User registered: " + user.getName());
        
        // Don't send password back
        user.setPassword(null);
        return user;
    }
    
    // POST - Login
    @PostMapping("/login")
    public User login(@RequestBody LoginRequest request) {
        System.out.println("🔐 Login attempt: " + request.getEmail());
        
        User user = users.stream()
            .filter(u -> u.getEmail().equals(request.getEmail()) 
                      && u.getPassword().equals(request.getPassword()))
            .findFirst()
            .orElse(null);
            
        if (user != null) {
            System.out.println("✅ Login successful: " + user.getName());
            // Don't send password
            user.setPassword(null);
            return user;
        } else {
            System.out.println("❌ Login failed");
            return null;
        }
    }
    
    // Health check
    @GetMapping("/health")
    public String health() {
        return "✅ User Service is running!";
    }
}

// Simple login request class
class LoginRequest {
    private String email;
    private String password;
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
