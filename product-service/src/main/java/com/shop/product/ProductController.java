package com.shop.product;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")  // Allow frontend to connect
public class ProductController {
    
    // Simple in-memory list (like a simple database)
    private List<Product> products = new ArrayList<>();
    
    // Constructor - adds some initial products
    public ProductController() {
        products.add(new Product(1L, "Laptop", "Powerful gaming laptop", 1299.99, 10, 
            "https://images.unsplash.com/photo-1603302576837-37561b2e2302?w=300"));
        products.add(new Product(2L, "Smartphone", "Latest model with great camera", 899.99, 25, 
            "https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?w=300"));
        products.add(new Product(3L, "Headphones", "Noise-cancelling wireless headphones", 199.99, 50, 
            "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=300"));
        products.add(new Product(4L, "Smart Watch", "Fitness tracking smartwatch", 299.99, 30, 
            "https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=300"));
    }
    
    // GET all products
    @GetMapping
    public List<Product> getAllProducts() {
        System.out.println("📦 Getting all products");
        return products;
    }
    
    // GET one product by ID
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        System.out.println("🔍 Getting product: " + id);
        return products.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst()
            .orElse(null);
    }
    
    // POST - Add new product
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        product.setId((long) (products.size() + 1));
        products.add(product);
        System.out.println("✅ Product added: " + product.getName());
        return product;
    }
    
    // PUT - Reduce stock (when someone orders)
    @PutMapping("/{id}/reduce-stock")
    public Product reduceStock(@PathVariable Long id, @RequestBody Integer quantity) {
        Product product = products.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst()
            .orElse(null);
            
        if (product != null && product.getStock() >= quantity) {
            product.setStock(product.getStock() - quantity);
            System.out.println("📉 Stock reduced for: " + product.getName());
            return product;
        }
        return null;
    }
    
    // Health check
    @GetMapping("/health")
    public String health() {
        return "✅ Product Service is running!";
    }
}
