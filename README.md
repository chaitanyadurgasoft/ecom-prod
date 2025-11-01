# 🛍️ Simple SpringBoot Shop - Microservices E-Commerce Application

A beginner-friendly e-commerce application built with Spring Boot microservices architecture and Docker.

## 📋 Table of Contents
- [Project Overview](#project-overview)
- [Architecture](#architecture)
- [Prerequisites](#prerequisites)
- [Quick Start](#quick-start)
- [Services](#services)
- [API Endpoints](#api-endpoints)
- [Development Guide](#development-guide)
- [Troubleshooting](#troubleshooting)

---

## 🎯 Project Overview

This is a **simple e-commerce application** perfect for learning microservices architecture. It includes:

- ✅ **Product Management** - Browse and manage products
- ✅ **User Authentication** - Login and registration
- ✅ **Shopping Cart** - Add products to cart
- ✅ **Order Processing** - Place orders
- ✅ **Notifications** - Order confirmation emails
- ✅ **API Gateway** - Single entry point for all services
- ✅ **Beautiful Frontend** - Modern, responsive UI

---

## 🏗️ Architecture

```
┌─────────────────┐
│    Frontend     │ (Port 3000)
│   (Nginx)       │
└────────┬────────┘
         │
         ↓
┌─────────────────┐
│  API Gateway    │ (Port 8080)
└────────┬────────┘
         │
    ┌────┴────┬──────────┬──────────┐
    ↓         ↓          ↓          ↓
┌─────────┐ ┌──────┐ ┌──────┐ ┌──────────┐
│ Product │ │ Order│ │ User │ │Notification│
│ Service │ │Service│ │Service│ │ Service   │
│ :8081   │ │:8082 │ │:8083 │ │  :8084    │
└─────────┘ └──────┘ └──────┘ └──────────┘
```

### Services:
1. **Frontend** - User interface (Nginx serving HTML/CSS/JS)
2. **API Gateway** - Routes requests to appropriate microservices
3. **Product Service** - Manages products catalog
4. **Order Service** - Handles order processing
5. **User Service** - Manages user authentication
6. **Notification Service** - Sends order confirmations

---

## 📦 Prerequisites

Before you start, make sure you have:

1. **Docker Desktop** (or Docker Engine + Docker Compose)
   - Download: https://www.docker.com/products/docker-desktop
   - Version: 20.10+ recommended

2. **Java 17** (only for local development without Docker)
   - Download: https://adoptium.net/

3. **Maven 3.8+** (only for local development without Docker)
   - Download: https://maven.apache.org/download.cgi

4. **Git** (to clone the repository)
   - Download: https://git-scm.com/downloads

---

## 🚀 Quick Start

### Option 1: Using Docker (Recommended for Beginners)

1. **Clone the repository**
```bash
git clone <your-repo-url>
cd simple-springboot-shop
```

2. **Start all services with Docker Compose**
```bash
docker-compose up --build
```

This will:
- Build all microservices
- Start all containers
- Set up networking between services
- Start the frontend

3. **Access the application**
- Frontend: http://localhost:3000
- API Gateway: http://localhost:8080/api/health

4. **Stop all services**
```bash
docker-compose down
```

### Option 2: Run Locally (For Development)

1. **Start each service individually** (open 5 terminals):

Terminal 1 - Product Service:
```bash
cd product-service
mvn spring-boot:run
```

Terminal 2 - Order Service:
```bash
cd order-service
mvn spring-boot:run
```

Terminal 3 - User Service:
```bash
cd user-service
mvn spring-boot:run
```

Terminal 4 - Notification Service:
```bash
cd notification-service
mvn spring-boot:run
```

Terminal 5 - API Gateway:
```bash
cd api-gateway
mvn spring-boot:run
```

2. **Open the frontend**
```bash
# Open frontend/index.html in your browser
# Or use a local web server
cd frontend
python -m http.server 3000  # Python 3
# OR
php -S localhost:3000       # PHP
```

---

## 🔧 Services

### Frontend (Port 3000)
- **Technology**: HTML, CSS, JavaScript
- **Server**: Nginx
- **Purpose**: User interface for the e-commerce application

### API Gateway (Port 8080)
- **Technology**: Spring Boot
- **Purpose**: 
  - Single entry point for all API requests
  - Routes requests to appropriate microservices
  - Simplifies frontend configuration

### Product Service (Port 8081)
- **Technology**: Spring Boot
- **Purpose**: 
  - Manage product catalog
  - Handle product CRUD operations
  - Track inventory

### Order Service (Port 8082)
- **Technology**: Spring Boot
- **Purpose**: 
  - Process customer orders
  - Calculate order totals
  - Communicate with notification service

### User Service (Port 8083)
- **Technology**: Spring Boot
- **Purpose**: 
  - User registration
  - User authentication
  - User management

### Notification Service (Port 8084)
- **Technology**: Spring Boot
- **Purpose**: 
  - Send order confirmations
  - Handle email notifications (simulated)

---

## 📡 API Endpoints

All API requests should go through the **API Gateway** at `http://localhost:8080/api`

### Products API

```http
GET    /api/products          # Get all products
GET    /api/products/{id}     # Get product by ID
POST   /api/products          # Add new product
PUT    /api/products/{id}/reduce-stock  # Reduce stock
```

### Users API

```http
GET    /api/users             # Get all users
POST   /api/users/register    # Register new user
POST   /api/users/login       # Login user
```

**Register/Login Request Body:**
```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "password123"
}
```

### Orders API

```http
GET    /api/orders            # Get all orders
GET    /api/orders/user/{userId}  # Get orders by user
POST   /api/orders            # Create new order
PUT    /api/orders/{id}/status    # Update order status
```

**Create Order Request Body:**
```json
{
  "userId": 1,
  "items": [
    {
      "productId": 1,
      "productName": "Laptop",
      "quantity": 1,
      "price": 1299.99
    }
  ],
  "total": 1299.99
}
```

### Notifications API

```http
POST   /api/notifications/send          # Send notification
POST   /api/notifications/order-created # Order notification
```

---

## 💻 Development Guide

### Project Structure

```
simple-springboot-shop/
├── api-gateway/              # API Gateway service
│   ├── src/
│   ├── Dockerfile
│   └── pom.xml
├── product-service/          # Product microservice
│   ├── src/
│   ├── Dockerfile
│   └── pom.xml
├── order-service/            # Order microservice
│   ├── src/
│   ├── Dockerfile
│   └── pom.xml
├── user-service/             # User microservice
│   ├── src/
│   ├── Dockerfile
│   └── pom.xml
├── notification-service/     # Notification microservice
│   ├── src/
│   ├── Dockerfile
│   └── pom.xml
├── frontend/                 # Frontend files
│   └── index.html
├── docker-compose.yml        # Docker orchestration
├── nginx.conf               # Nginx configuration
├── pom.xml                  # Parent POM
└── README.md                # This file
```

### Adding a New Microservice

1. Create new directory: `new-service/`
2. Add `pom.xml` with Spring Boot dependencies
3. Create service code in `src/main/java/`
4. Add `Dockerfile` (copy from existing services)
5. Add service to `docker-compose.yml`
6. Add routing in API Gateway

### Testing Services

**Health Checks:**
```bash
# Check if services are running
curl http://localhost:8080/api/health
curl http://localhost:8081/api/products/health
curl http://localhost:8082/api/orders/health
curl http://localhost:8083/api/users/health
curl http://localhost:8084/api/notifications/health
```

**Test Product API:**
```bash
# Get all products
curl http://localhost:8080/api/products
```

**Test User Registration:**
```bash
curl -X POST http://localhost:8080/api/users/register \
  -H "Content-Type: application/json" \
  -d '{"name":"Test User","email":"test@test.com","password":"test123"}'
```

---

## 🐛 Troubleshooting

### Docker Issues

**Problem**: `docker-compose up` fails with port conflicts
**Solution**: 
```bash
# Check what's using the ports
netstat -ano | findstr :8080   # Windows
lsof -i :8080                  # Mac/Linux

# Kill the process or change ports in docker-compose.yml
```

**Problem**: Services can't communicate with each other
**Solution**: 
```bash
# Recreate the network
docker-compose down
docker network prune
docker-compose up --build
```

**Problem**: Frontend shows "Service unavailable"
**Solution**: 
```bash
# Check if all services are running
docker-compose ps

# View logs for specific service
docker-compose logs api-gateway
docker-compose logs product-service
```

### Build Issues

**Problem**: Maven build fails
**Solution**: 
```bash
# Clean and rebuild
mvn clean install -DskipTests

# Or with Docker
docker-compose build --no-cache
```

**Problem**: Java version mismatch
**Solution**: 
```bash
# Check Java version (should be 17)
java -version

# Set JAVA_HOME if needed
export JAVA_HOME=/path/to/java17  # Mac/Linux
set JAVA_HOME=C:\path\to\java17   # Windows
```

### Application Issues

**Problem**: Products not loading
**Solution**: 
- Check Product Service logs: `docker-compose logs product-service`
- Verify API Gateway is routing correctly
- Check browser console for errors

**Problem**: Login not working
**Solution**: 
- Check User Service logs: `docker-compose logs user-service`
- Verify request payload format
- Check CORS settings

**Problem**: Orders not being created
**Solution**: 
- Check Order Service logs: `docker-compose logs order-service`
- Verify user is logged in
- Check cart has items

---

## 📚 Learning Resources

### Microservices Architecture
- [Martin Fowler - Microservices](https://martinfowler.com/articles/microservices.html)
- [Spring Boot Microservices Tutorial](https://spring.io/microservices)

### Docker
- [Docker Documentation](https://docs.docker.com/)
- [Docker Compose Documentation](https://docs.docker.com/compose/)

### Spring Boot
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Building REST APIs with Spring](https://spring.io/guides/tutorials/rest/)

---

## 🤝 Contributing

This is a learning project! Feel free to:
1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Open a Pull Request

---

## 📝 License

This project is open source and available for educational purposes.

---

## 🎓 What You'll Learn

By working with this project, you'll learn:

- ✅ **Microservices Architecture** - How to structure applications as independent services
- ✅ **Spring Boot** - Building REST APIs with Java
- ✅ **Docker** - Containerizing applications
- ✅ **Docker Compose** - Orchestrating multiple containers
- ✅ **API Gateway Pattern** - Routing and managing API requests
- ✅ **Service Communication** - How microservices talk to each other
- ✅ **REST API Design** - Creating clean, RESTful endpoints
- ✅ **Frontend Integration** - Connecting UI to backend services

---

## 📧 Support

If you encounter any issues:
1. Check the Troubleshooting section above
2. Review service logs: `docker-compose logs [service-name]`
3. Create an issue in the repository

---

## 🎉 Happy Coding!

Enjoy learning microservices with Spring Boot! 🚀
