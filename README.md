# Decorator Facade Patterns E-Commerce Project

## Overview
This Spring Boot project demonstrates the implementation of the Decorator and Facade design patterns within an e-commerce checkout system. It provides a RESTful API for placing orders with multiple payment options, including credit cards and PayPal. The payment process is enhanced with decorators that handle discounts, cashback, and fraud detection without changing the core payment logic.

## Features
- Place orders with payment processing through `/api/checkout/` (POST).
- Supports customer creation along with the order.
- CRUD operations for orders through `/api/checkout/orders` endpoints.
- Data persistence using Spring Data JPA.
- Clean layering of controllers, services (facade), and repositories.
- Extensible design for adding new payment methods or decorators.

## Technology Stack
- Java 21, Spring Boot 3.5.6
- Spring Data JPA and Hibernate ORM
- Maven for build automation
- H2 (in-memory) or PostgreSQL for database

## Setup Instructions
1. Clone the repository:  
git clone <your-repository-url>
cd decorator-facade-patterns

2. Build the project:  
mvn clean install

3. Run the application:  
mvn clean package
docker compose build
docker compose up

4. Access the API at `http://localhost:8080`

## API Usage and Testing

### 1. Place Order with Customer and Payment (POST)
- **Endpoint:** `POST /api/checkout/`
- **Request Body Example:**  
{
"paymentMethod": "credit_card",
"amount": 1000,
"customerName": "Jane Doe",
}

- **Success Response:**  
{
"success": true,
"message": "Order processed successfully. Payment completed with discount and cashback."
}


### 2. Retrieve All Orders (GET)
- **Endpoint:** `GET /api/checkout/orders`
- **Response:** Array of all orders in JSON format.

### 3. Retrieve Order by ID (GET)
- **Endpoint:** `GET /api/checkout/orders/{id}`
- **Response:** Order object or 404 Not Found if not present.

### 4. Update Order (PUT)
- **Endpoint:** `PUT /api/checkout/orders/{id}`
- **Request Body Example:**  
{
"amount": 1200
}

- **Response:** Updated order object or 404 if not found.

---
