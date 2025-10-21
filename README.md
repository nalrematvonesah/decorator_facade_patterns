Decorator Facade Patterns E-commerce Project
Project Overview
This Spring Boot project demonstrates key design patterns—the Decorator and Facade—implemented in an e-commerce checkout system.
Users can place orders with payment via supported methods such as credit card and PayPal. Payment processing is enhanced with decorators for discount, cashback, and fraud detection without modifying core payment logic.

Features
REST API for order checkout at /api/checkout/ (POST).

Supports dynamic payment methods with flexible decorator pattern extensions.

CRUD operations on orders via /api/checkout/orders endpoints.

Data persistence using Spring Data JPA.

Layered architecture separating API, service, and database concerns.

Tech Stack
Java 17, Spring Boot 3.5.6

Spring Data JPA & Hibernate ORM

Maven build system

H2 (in-memory) or PostgreSQL database support

Setup and Running
Clone repository:

text
git clone <repo-url>
cd decorator-facade-patterns
Build and run the application:

text
mvn clean install
mvn spring-boot:run
Access API endpoints on http://localhost:8080

API Requests for Testing
1. Place Order - Checkout (POST)
URL: http://localhost:8080/api/checkout/

Body:

json
{
  "paymentMethod": "credit_card",
  "amount": 1000
}
Response (Success):

json
{
  "success": true,
  "message": "Order processed successfully. Payment completed with discount and cashback."
}
2. List All Orders (GET)
URL: http://localhost:8080/api/checkout/orders

Response: JSON array of orders

3. Get Order By ID (GET)
URL: http://localhost:8080/api/checkout/orders/{id}

Response: JSON object or 404 if not found

4. Update Order (PUT)
URL: http://localhost:8080/api/checkout/orders/{id}

Body example:

json
{
  "amount": 1200
}
Response: Updated order JSON

5. Delete Order (DELETE)
URL: http://localhost:8080/api/checkout/orders/{id}

Response: 204 No Content or 404 if not found

Notes
Use Postman or similar HTTP clients to test any of these endpoints.

Extend payment methods or decorators by adding new classes without changing existing code.

Add validations and robust error handling for production readiness.

Recommended to set up automated tests covering these API workflows.
