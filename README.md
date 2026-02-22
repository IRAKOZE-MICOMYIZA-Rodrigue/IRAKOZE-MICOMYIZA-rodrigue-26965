# RESTful API Assignment - Product Management System

A Spring Boot RESTful API for managing products with full CRUD operations.

## Technologies Used

- Java 21
- Spring Boot 4.0.2
- Spring Data JPA
- PostgreSQL
- Maven

## Prerequisites

- JDK 21 or higher
- PostgreSQL database
- Maven

## Database Setup

1. Create a PostgreSQL database:
```sql
CREATE DATABASE ecommerce_db;
```

2. Update `src/main/resources/application.properties` with your database credentials:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

## Running the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8081`

## API Endpoints

### 1. Create Product
**POST** `/api/products/save`

**Request Body:**
```json
{
  "name": "Laptop",
  "description": "Gaming laptop",
  "price": 1200.00,
  "category": "Electronics",
  "stockQuantity": 10,
  "brand": "Dell"
}
```

### 2. Get All Products
**GET** `/api/products`

### 3. Get Product by ID
**GET** `/api/products/{id}`

### 4. Update Product
**PUT** `/api/products/{id}`

**Request Body:**
```json
{
  "name": "Laptop Pro",
  "description": "Updated gaming laptop",
  "price": 1500.00,
  "category": "Electronics",
  "stockQuantity": 5,
  "brand": "Dell"
}
```

### 5. Delete Product
**DELETE** `/api/products/{id}`

## Testing the API

### Using PowerShell

**Create Product:**
```powershell
Invoke-RestMethod -Uri "http://localhost:8081/api/products/save" -Method POST -ContentType "application/json" -Body '{"name":"Laptop","description":"Gaming laptop","price":1200.00,"category":"Electronics","stockQuantity":10,"brand":"Dell"}'
```

**Get All Products:**
```powershell
Invoke-RestMethod -Uri "http://localhost:8081/api/products" -Method GET
```

**Get Product by ID:**
```powershell
Invoke-RestMethod -Uri "http://localhost:8081/api/products/1" -Method GET
```

**Update Product:**
```powershell
Invoke-RestMethod -Uri "http://localhost:8081/api/products/1" -Method PUT -ContentType "application/json" -Body '{"name":"Laptop Pro","description":"Updated","price":1500.00,"category":"Electronics","stockQuantity":5,"brand":"Dell"}'
```

**Delete Product:**
```powershell
Invoke-RestMethod -Uri "http://localhost:8081/api/products/1" -Method DELETE
```

## Project Structure

```
src/
├── main/
│   ├── java/auca/ac/rw/restfullApiAssignment/
│   │   ├── controller/
│   │   │   └── ProductController.java
│   │   ├── modal/
│   │   │   └── Product.java
│   │   ├── repository/
│   │   │   └── ProductRepository.java
│   │   ├── service/
│   │   │   └── ProductService.java
│   │   └── RestfullApiAssignmentApplication.java
│   └── resources/
│       └── application.properties
```

## Author

IRAKOZE MICOMYIZA Rodrigue - 26965
