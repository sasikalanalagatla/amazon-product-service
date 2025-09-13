#!/bin/bash
echo "🚀 Starting Amazon Product Service..."
echo "Pulling image from Docker Hub: sasikala13/amazon-product:latest"
docker compose pull

echo "Starting containers..."
docker compose up -d

echo "⏳ Waiting for database to initialize..."
sleep 20

echo "📦 Importing 34 products..."
docker cp products_data.sql product-postgres:/tmp/
docker exec product-postgres psql -U postgres -d amazon_product -f /tmp/products_data.sql

echo "✅ Setup complete! API is running at:"
echo "🔍 Health: http://localhost:8080/actuator/health"
echo "📦 Products: http://localhost:8080/api/products"
