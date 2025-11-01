# 🚀 Quick Start Guide - For Complete Beginners

This guide will help you get the Simple SpringBoot Shop running on your computer!

## 📋 What You Need

1. **Docker Desktop** - A tool that runs applications in containers
   - Download from: https://www.docker.com/products/docker-desktop
   - Install it and make sure it's running (you'll see a whale icon)

2. **A Text Editor** (optional, for viewing code)
   - VS Code: https://code.visualstudio.com/
   - Or any text editor you like

## 🎯 Step-by-Step Instructions

### Step 1: Get the Code

1. Download this project (or clone it using Git)
2. Extract it to a folder (e.g., `C:\projects\simple-shop` or `~/projects/simple-shop`)

### Step 2: Open Terminal/Command Prompt

**On Windows:**
- Press `Windows Key + R`
- Type `cmd` and press Enter
- Navigate to your project folder:
  ```cmd
  cd C:\projects\simple-shop
  ```

**On Mac/Linux:**
- Open Terminal app
- Navigate to your project folder:
  ```bash
  cd ~/projects/simple-shop
  ```

### Step 3: Start Everything!

**On Windows:**
```cmd
start.bat
```

**On Mac/Linux:**
```bash
./start.sh
```

This will:
- ✅ Check if Docker is running
- ✅ Build all the services
- ✅ Start all the containers
- ✅ Set up the network
- ✅ Start the frontend

**Wait about 2-3 minutes** for everything to start up!

### Step 4: Open Your Browser

Once everything is ready, open your web browser and go to:

```
http://localhost:3000
```

You should see the **Simple Shop** homepage! 🎉

## 🎮 How to Use the Application

### 1. Browse Products
- You'll see 4 products on the homepage
- Click on any product to add it to your cart

### 2. Register an Account
- Click the "Login" button
- Fill in your details
- Click "Register"

### 3. Add Products to Cart
- Click on products to add them to cart
- Click "🛒 Cart" button to view your cart

### 4. Place an Order
- In your cart, click "🎉 Checkout"
- Your order will be created!
- Check the terminal/command prompt to see the order notification

## 🛠️ Useful Commands

### View What's Running
```bash
docker-compose ps
```

### View Logs (See What's Happening)
```bash
# All services
docker-compose logs -f

# Just one service
docker-compose logs -f product-service
```

### Stop Everything
```bash
docker-compose down
```

### Restart Everything
```bash
docker-compose restart
```

### Clean Restart (If Something Goes Wrong)
```bash
docker-compose down
docker-compose up --build -d
```

## 🐛 Troubleshooting

### Problem: "Docker is not running"
**Solution:** 
1. Open Docker Desktop application
2. Wait for it to say "Docker Desktop is running"
3. Try starting again

### Problem: "Port already in use"
**Solution:** 
1. Stop other applications using ports 3000, 8080, 8081, 8082, 8083, 8084
2. Or change the ports in `docker-compose.yml`

### Problem: "Cannot connect to the application"
**Solution:** 
1. Wait 2-3 minutes after starting
2. Check if all services are running: `docker-compose ps`
3. Look at logs: `docker-compose logs`

### Problem: "Frontend shows errors"
**Solution:** 
1. Open browser console (F12)
2. Check for error messages
3. Make sure all services are green when you run `docker-compose ps`

## 🧪 Test the APIs Manually

You can test the APIs using your browser or a tool like Postman:

### Check if Everything is Running
```
http://localhost:8080/api/health
```

### Get All Products
```
http://localhost:8080/api/products
```

### Get All Users
```
http://localhost:8080/api/users
```

## 📚 Understanding the Project

### What are Microservices?
Think of it like a restaurant:
- **Product Service** = Menu (shows what's available)
- **User Service** = Waiter (handles customers)
- **Order Service** = Kitchen (processes orders)
- **Notification Service** = Receipt printer (confirms orders)
- **API Gateway** = Host/Hostess (directs everyone)
- **Frontend** = Dining area (where customers sit)

### Why Docker?
- **Without Docker**: You'd need to install Java, Maven, and run 5 separate programs
- **With Docker**: One command starts everything!

### What's Docker Compose?
It's like a conductor for an orchestra - it makes sure all the services (musicians) start at the right time and work together.

## 🎓 Next Steps

Now that you have it running, try:

1. **Explore the Code**
   - Look at `frontend/index.html` to see the UI code
   - Check `product-service/src/main/java/` to see backend code

2. **Make Changes**
   - Add a new product in `ProductController.java`
   - Change colors in `index.html`
   - Restart: `docker-compose restart [service-name]`

3. **Learn More**
   - Read the main README.md for detailed information
   - Check out Spring Boot tutorials
   - Learn about REST APIs

## 💡 Tips

- **Keep Docker Desktop running** while working on the project
- **Check logs often** to see what's happening: `docker-compose logs -f`
- **Don't be afraid to restart**: `docker-compose restart`
- **Clean slate**: `docker-compose down && docker-compose up --build -d`

## ❓ Still Having Issues?

1. Make sure Docker Desktop is running
2. Try restarting Docker Desktop
3. Check the main README.md for more detailed troubleshooting
4. Check service logs: `docker-compose logs [service-name]`

## 🎉 Success!

If you see the shop homepage and can browse products, congratulations! 🎊

You now have a working microservices application running on your computer!

---

**Remember**: 
- Frontend: http://localhost:3000
- API Gateway: http://localhost:8080
- Stop everything: `docker-compose down`
- Start again: `./start.sh` (Mac/Linux) or `start.bat` (Windows)

Happy learning! 🚀
