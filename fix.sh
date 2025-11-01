#!/bin/bash

echo "🔧 Complete Fix for Simple Shop Application"
echo "============================================"
echo ""

# Step 1: Stop everything
echo "1️⃣ Stopping all containers..."
docker-compose down -v
echo "   ✅ Containers stopped"
echo ""

# Step 2: Clean up
echo "2️⃣ Cleaning Docker cache..."
docker system prune -f
echo "   ✅ Cache cleaned"
echo ""

# Step 3: Rebuild
echo "3️⃣ Rebuilding all services (this may take a few minutes)..."
docker compose build --no-cache
echo "   ✅ Services rebuilt"
echo ""

# Step 4: Start services
echo "4️⃣ Starting all services..."
docker compose up -d
echo "   ✅ Services started"
echo ""

# Step 5: Wait for services to initialize
echo "5️⃣ Waiting for services to initialize..."
echo "   This takes about 60 seconds..."
for i in {60..1}; do
    echo -ne "   ⏳ $i seconds remaining...\r"
    sleep 1
done
echo ""
echo "   ✅ Initialization complete"
echo ""

# Step 6: Check status
echo "6️⃣ Checking service status..."
docker compose ps
echo ""

# Step 7: Test services
echo "7️⃣ Testing services..."
echo ""

echo "   Testing API Gateway health..."
GATEWAY_HEALTH=$(curl -s http://localhost:8080/api/health)
if [[ $GATEWAY_HEALTH == *"running"* ]]; then
    echo "   ✅ API Gateway: OK"
else
    echo "   ❌ API Gateway: FAILED"
    echo "   Response: $GATEWAY_HEALTH"
fi
echo ""

echo "   Testing Product Service..."
PRODUCTS=$(curl -s http://localhost:8080/api/products)
if [[ $PRODUCTS == *"Laptop"* ]]; then
    echo "   ✅ Product Service: OK"
else
    echo "   ❌ Product Service: FAILED"
    echo "   Response: $PRODUCTS"
fi
echo ""

echo "   Testing User Service..."
USERS=$(curl -s http://localhost:8080/api/users)
if [[ $USERS == *"["* ]]; then
    echo "   ✅ User Service: OK"
else
    echo "   ❌ User Service: FAILED"  
    echo "   Response: $USERS"
fi
echo ""

# Step 8: Final message
echo "============================================"
echo "✅ Setup Complete!"
echo ""
echo "📱 Access your application:"
echo "   Frontend: http://localhost:3000"
echo "   API Gateway: http://localhost:8080/api/health"
echo ""
echo "📊 View logs:"
echo "   docker-compose logs -f"
echo ""
echo "🛑 Stop services:"
echo "   docker-compose down"
echo ""
echo "Try opening http://localhost:3000 in your browser now!"
echo "============================================"
