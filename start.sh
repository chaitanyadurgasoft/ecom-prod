#!/bin/bash

# Colors for output
GREEN='\033[0;32m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo -e "${BLUE}╔════════════════════════════════════════════════╗${NC}"
echo -e "${BLUE}║   🛍️  Simple SpringBoot Shop - Startup       ║${NC}"
echo -e "${BLUE}╚════════════════════════════════════════════════╝${NC}"
echo ""

# Check if Docker is installed
if ! command -v docker &> /dev/null; then
    echo -e "${YELLOW}⚠️  Docker is not installed!${NC}"
    echo "Please install Docker Desktop from: https://www.docker.com/products/docker-desktop"
    exit 1
fi

# Check if Docker is running
if ! docker info &> /dev/null; then
    echo -e "${YELLOW}⚠️  Docker is not running!${NC}"
    echo "Please start Docker Desktop and try again."
    exit 1
fi

echo -e "${GREEN}✅ Docker is installed and running${NC}"
echo ""

# Stop any existing containers
echo -e "${BLUE}🛑 Stopping any existing containers...${NC}"
docker-compose down &> /dev/null

# Build and start services
echo -e "${BLUE}🏗️  Building and starting all services...${NC}"
echo -e "${YELLOW}This may take a few minutes on first run...${NC}"
echo ""

docker-compose up --build -d

# Wait for services to be ready
echo ""
echo -e "${BLUE}⏳ Waiting for services to be ready...${NC}"
sleep 10

# Check if services are running
echo ""
echo -e "${BLUE}🔍 Checking service health...${NC}"

services=("api-gateway:8080" "product-service:8081" "order-service:8082" "user-service:8083" "notification-service:8084" "frontend:3000")

for service in "${services[@]}"; do
    IFS=':' read -r name port <<< "$service"
    if docker ps | grep -q $name; then
        echo -e "${GREEN}✅ $name is running on port $port${NC}"
    else
        echo -e "${YELLOW}⚠️  $name may not be ready yet${NC}"
    fi
done

echo ""
echo -e "${GREEN}╔════════════════════════════════════════════════╗${NC}"
echo -e "${GREEN}║           🎉 Services Started!                 ║${NC}"
echo -e "${GREEN}╚════════════════════════════════════════════════╝${NC}"
echo ""
echo -e "${BLUE}📱 Access the application:${NC}"
echo -e "   Frontend:    ${GREEN}http://localhost:3000${NC}"
echo -e "   API Gateway: ${GREEN}http://localhost:8080/api/health${NC}"
echo ""
echo -e "${BLUE}📊 View logs:${NC}"
echo -e "   All services:     ${YELLOW}docker-compose logs -f${NC}"
echo -e "   Specific service: ${YELLOW}docker-compose logs -f [service-name]${NC}"
echo ""
echo -e "${BLUE}🛑 Stop services:${NC}"
echo -e "   ${YELLOW}docker-compose down${NC}"
echo ""
echo -e "${GREEN}Happy coding! 🚀${NC}"
