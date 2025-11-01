package com.shop.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class GatewayController {
    
    @Autowired
    private RestTemplate restTemplate;
    
    // Service URLs - these will be container names in Docker
    private static final String PRODUCT_SERVICE = "http://product-service:8081";
    private static final String ORDER_SERVICE = "http://order-service:8082";
    private static final String USER_SERVICE = "http://user-service:8083";
    private static final String NOTIFICATION_SERVICE = "http://notification-service:8084";
    
    // Route to Product Service
    @RequestMapping(value = "/products/**", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<String> routeToProductService(HttpServletRequest request, @RequestBody(required = false) Object body) {
        String path = request.getRequestURI().replace("/api/products", "");
        String url = PRODUCT_SERVICE + "/api/products" + path;
        
        System.out.println("📦 Routing to Product Service: " + url);
        return forwardRequest(url, request.getMethod(), body);
    }
    
    // Route to Order Service
    @RequestMapping(value = "/orders/**", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<String> routeToOrderService(HttpServletRequest request, @RequestBody(required = false) Object body) {
        String path = request.getRequestURI().replace("/api/orders", "");
        String url = ORDER_SERVICE + "/api/orders" + path;
        
        System.out.println("🛒 Routing to Order Service: " + url);
        return forwardRequest(url, request.getMethod(), body);
    }
    
    // Route to User Service
    @RequestMapping(value = "/users/**", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<String> routeToUserService(HttpServletRequest request, @RequestBody(required = false) Object body) {
        String path = request.getRequestURI().replace("/api/users", "");
        String url = USER_SERVICE + "/api/users" + path;
        
        System.out.println("👤 Routing to User Service: " + url);
        return forwardRequest(url, request.getMethod(), body);
    }
    
    // Route to Notification Service
    @RequestMapping(value = "/notifications/**", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<String> routeToNotificationService(HttpServletRequest request, @RequestBody(required = false) Object body) {
        String path = request.getRequestURI().replace("/api/notifications", "");
        String url = NOTIFICATION_SERVICE + "/api/notifications" + path;
        
        System.out.println("📧 Routing to Notification Service: " + url);
        return forwardRequest(url, request.getMethod(), body);
    }
    
    // Helper method to forward requests - FIXED to prevent chunked encoding issues
    private ResponseEntity<String> forwardRequest(String url, String method, Object body) {
        try {
            // Create headers without chunked encoding
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Accept", "application/json");
            // Explicitly disable chunked encoding
            headers.setContentLength(0);
            
            HttpEntity<?> entity = new HttpEntity<>(body, headers);
            
            ResponseEntity<String> response = null;
            
            switch (method) {
                case "GET":
                    response = restTemplate.getForEntity(url, String.class);
                    break;
                case "POST":
                    response = restTemplate.postForEntity(url, entity, String.class);
                    break;
                case "PUT":
                    response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
                    break;
                case "DELETE":
                    response = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);
                    break;
            }
            
            // Create new response with clean headers (NO chunked encoding)
            HttpHeaders cleanHeaders = new HttpHeaders();
            cleanHeaders.setContentType(MediaType.APPLICATION_JSON);
            
            // Get response body
            String responseBody = response.getBody();
            if (responseBody != null) {
                // Set explicit content length instead of chunked encoding
                byte[] bodyBytes = responseBody.getBytes(StandardCharsets.UTF_8);
                cleanHeaders.setContentLength(bodyBytes.length);
            }
            
            return ResponseEntity
                    .status(response.getStatusCode())
                    .headers(cleanHeaders)
                    .body(responseBody);
                    
        } catch (Exception e) {
            System.err.println("❌ Error routing request to " + url + ": " + e.getMessage());
            e.printStackTrace();
            
            String errorBody = "{\"error\": \"Service temporarily unavailable\"}";
            HttpHeaders errorHeaders = new HttpHeaders();
            errorHeaders.setContentType(MediaType.APPLICATION_JSON);
            errorHeaders.setContentLength(errorBody.getBytes(StandardCharsets.UTF_8).length);
            
            return ResponseEntity
                    .status(HttpStatus.SERVICE_UNAVAILABLE)
                    .headers(errorHeaders)
                    .body(errorBody);
        }
    }
    
    // Health check
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        String body = "✅ API Gateway is running!";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setContentLength(body.getBytes(StandardCharsets.UTF_8).length);
        
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(body);
    }
}
