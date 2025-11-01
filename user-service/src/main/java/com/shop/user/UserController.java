package com.shop.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;
    
    // GET all users
    @GetMapping
    public List<User> getAllUsers() {
        System.out.println("👥 Getting all users from database");
        List<User> users = userRepository.findAll();
        users.forEach(u -> u.setPassword("***"));
        return users;
    }
    
    // POST - Register new user
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            System.out.println("❌ Email already exists: " + user.getEmail());
            return null;
        }
        
        User savedUser = userRepository.save(user);
        System.out.println("✅ User registered in database: " + savedUser.getName());
        
        savedUser.setPassword(null);
        return savedUser;
    }
    
    // POST - Login
    @PostMapping("/login")
    public User login(@RequestBody LoginRequest request) {
        System.out.println("🔐 Login attempt from database: " + request.getEmail());
        
        User user = userRepository.findByEmail(request.getEmail()).orElse(null);
        
        if (user != null && user.getPassword().equals(request.getPassword())) {
            System.out.println("✅ Login successful: " + user.getName());
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
        return "✅ User Service is running with Database!";
    }
}

class LoginRequest {
    private String email;
    private String password;
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
