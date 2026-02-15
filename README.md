# Spring Boot RESTful API Project

This project contains implementations for all 5 questions plus the bonus question for the Spring Boot RESTful API assignment.

## Project Structure

```
src/main/java/com/restapi/
├── RestfulApiApplication.java (Main Application)
├── library/
│   ├── model/Book.java
│   └── controller/BookController.java
├── student/
│   ├── model/Student.java
│   └── controller/StudentController.java
├── restaurant/
│   ├── model/MenuItem.java
│   └── controller/MenuController.java
├── ecommerce/
│   ├── model/Product.java
│   └── controller/ProductController.java
├── task/
│   ├── model/Task.java
│   └── controller/TaskController.java
└── user/
    ├── model/UserProfile.java
    ├── model/ApiResponse.java
    └── controller/UserProfileController.java
```

## How to Run

1. Make sure you have Java 17 or higher installed
2. Navigate to the project directory
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
   Or if using an IDE, run `RestfulApiApplication.java`
4. The application will start on `http://localhost:8081`

## Question 1: Library Book Management API

### Endpoints

#### GET /api/books
Get all books
- **Response**: 200 OK
```json
[
  {
    "id": 1,
    "title": "Clean Code",
    "author": "Robert Martin",
    "isbn": "978-0132350884",
    "publicationYear": 2008
  }
]
```

#### GET /api/books/{id}
Get book by ID
- **Example**: GET /api/books/1
- **Response**: 200 OK or 404 Not Found

#### GET /api/books/search?title={title}
Search books by title
- **Example**: GET /api/books/search?title=clean
- **Response**: 200 OK

#### POST /api/books
Add a new book
- **Request Body**:
```json
{
  "title": "Design Patterns",
  "author": "Gang of Four",
  "isbn": "978-0201633610",
  "publicationYear": 1994
}
```
- **Response**: 201 Created

#### DELETE /api/books/{id}
Delete a book
- **Example**: DELETE /api/books/1
- **Response**: 204 No Content or 404 Not Found

---

## Question 2: Student Registration API

### Endpoints

#### GET /api/students
Get all students
- **Response**: 200 OK

#### GET /api/students/{studentId}
Get student by ID
- **Example**: GET /api/students/1
- **Response**: 200 OK or 404 Not Found

#### GET /api/students/major/{major}
Get students by major
- **Example**: GET /api/students/major/Computer Science
- **Response**: 200 OK

#### GET /api/students/filter?gpa={minGpa}
Filter students by minimum GPA
- **Example**: GET /api/students/filter?gpa=3.5
- **Response**: 200 OK

#### POST /api/students
Register a new student
- **Request Body**:
```json
{
  "firstName": "Alice",
  "lastName": "Johnson",
  "email": "alice@email.com",
  "major": "Computer Science",
  "gpa": 3.7
}
```
- **Response**: 201 Created

#### PUT /api/students/{studentId}
Update student information
- **Example**: PUT /api/students/1
- **Response**: 200 OK or 404 Not Found

---

## Question 3: Restaurant Menu API

### Endpoints

#### GET /api/menu
Get all menu items
- **Response**: 200 OK

#### GET /api/menu/{id}
Get menu item by ID
- **Example**: GET /api/menu/1
- **Response**: 200 OK or 404 Not Found

#### GET /api/menu/category/{category}
Get items by category
- **Example**: GET /api/menu/category/Appetizer
- **Response**: 200 OK

#### GET /api/menu/available?available={true/false}
Get available/unavailable items
- **Example**: GET /api/menu/available?available=true
- **Response**: 200 OK

#### GET /api/menu/search?name={name}
Search menu items by name
- **Example**: GET /api/menu/search?name=chicken
- **Response**: 200 OK

#### POST /api/menu
Add new menu item
- **Request Body**:
```json
{
  "name": "Grilled Salmon",
  "description": "Fresh salmon with herbs",
  "price": 18.99,
  "category": "Main Course",
  "available": true
}
```
- **Response**: 201 Created

#### PUT /api/menu/{id}/availability
Toggle item availability
- **Example**: PUT /api/menu/1/availability
- **Response**: 200 OK or 404 Not Found

#### DELETE /api/menu/{id}
Remove menu item
- **Example**: DELETE /api/menu/1
- **Response**: 204 No Content or 404 Not Found

---

## Question 4: E-Commerce Product API

### Endpoints

#### GET /api/products
Get all products (with optional pagination)
- **Example**: GET /api/products?page=0&limit=5
- **Response**: 200 OK

#### GET /api/products/{productId}
Get product by ID
- **Example**: GET /api/products/1
- **Response**: 200 OK or 404 Not Found

#### GET /api/products/category/{category}
Get products by category
- **Example**: GET /api/products/category/Electronics
- **Response**: 200 OK

#### GET /api/products/brand/{brand}
Get products by brand
- **Example**: GET /api/products/brand/Apple
- **Response**: 200 OK

#### GET /api/products/search?keyword={keyword}
Search products by keyword
- **Example**: GET /api/products/search?keyword=phone
- **Response**: 200 OK

#### GET /api/products/price-range?min={min}&max={max}
Get products within price range
- **Example**: GET /api/products/price-range?min=100&max=500
- **Response**: 200 OK

#### GET /api/products/in-stock
Get products in stock
- **Response**: 200 OK

#### POST /api/products
Add new product
- **Request Body**:
```json
{
  "name": "iPad Pro",
  "description": "Latest Apple tablet",
  "price": 1099.99,
  "category": "Electronics",
  "stockQuantity": 25,
  "brand": "Apple"
}
```
- **Response**: 201 Created

#### PUT /api/products/{productId}
Update product details
- **Example**: PUT /api/products/1
- **Response**: 200 OK or 404 Not Found

#### PATCH /api/products/{productId}/stock?quantity={quantity}
Update stock quantity
- **Example**: PATCH /api/products/1/stock?quantity=50
- **Response**: 200 OK or 404 Not Found

#### DELETE /api/products/{productId}
Delete product
- **Example**: DELETE /api/products/1
- **Response**: 204 No Content or 404 Not Found

---

## Question 5: Task Management API

### Endpoints

#### GET /api/tasks
Get all tasks
- **Response**: 200 OK

#### GET /api/tasks/{taskId}
Get task by ID
- **Example**: GET /api/tasks/1
- **Response**: 200 OK or 404 Not Found

#### GET /api/tasks/status?completed={true/false}
Get tasks by completion status
- **Example**: GET /api/tasks/status?completed=false
- **Response**: 200 OK

#### GET /api/tasks/priority/{priority}
Get tasks by priority
- **Example**: GET /api/tasks/priority/HIGH
- **Response**: 200 OK

#### POST /api/tasks
Create new task
- **Request Body**:
```json
{
  "title": "Code review",
  "description": "Review pull requests",
  "completed": false,
  "priority": "MEDIUM",
  "dueDate": "2024-12-22"
}
```
- **Response**: 201 Created

#### PUT /api/tasks/{taskId}
Update task
- **Example**: PUT /api/tasks/1
- **Response**: 200 OK or 404 Not Found

#### PATCH /api/tasks/{taskId}/complete
Mark task as completed
- **Example**: PATCH /api/tasks/1/complete
- **Response**: 200 OK or 404 Not Found

#### DELETE /api/tasks/{taskId}
Delete task
- **Example**: DELETE /api/tasks/1
- **Response**: 204 No Content or 404 Not Found

---

## Bonus Question: User Profile API

### Endpoints

#### GET /api/users
Get all users
- **Response**: 200 OK
```json
{
  "success": true,
  "message": "Users retrieved successfully",
  "data": [...]
}
```

#### GET /api/users/{userId}
Get user by ID
- **Example**: GET /api/users/1
- **Response**: 200 OK or 404 Not Found

#### GET /api/users/search/username?username={username}
Search by username
- **Example**: GET /api/users/search/username?username=john
- **Response**: 200 OK

#### GET /api/users/search/country?country={country}
Search by country
- **Example**: GET /api/users/search/country?country=USA
- **Response**: 200 OK

#### GET /api/users/search/age-range?min={min}&max={max}
Search by age range
- **Example**: GET /api/users/search/age-range?min=25&max=35
- **Response**: 200 OK

#### POST /api/users
Create user profile
- **Request Body**:
```json
{
  "username": "alice_wonder",
  "email": "alice@example.com",
  "fullName": "Alice Wonder",
  "age": 27,
  "country": "Australia",
  "bio": "Software engineer",
  "active": true
}
```
- **Response**: 201 Created

#### PUT /api/users/{userId}
Update user profile
- **Example**: PUT /api/users/1
- **Response**: 200 OK or 404 Not Found

#### PATCH /api/users/{userId}/activate
Activate user profile
- **Example**: PATCH /api/users/1/activate
- **Response**: 200 OK or 404 Not Found

#### PATCH /api/users/{userId}/deactivate
Deactivate user profile
- **Example**: PATCH /api/users/1/deactivate
- **Response**: 200 OK or 404 Not Found

#### DELETE /api/users/{userId}
Delete user profile
- **Example**: DELETE /api/users/1
- **Response**: 200 OK or 404 Not Found

---

## Testing with Postman

1. Import the endpoints into Postman
2. Set the base URL to `http://localhost:8081`
3. Test each endpoint with the provided examples
4. For POST/PUT requests, set Content-Type header to `application/json`

## HTTP Status Codes Used

- **200 OK**: Successful GET/PUT/PATCH requests
- **201 Created**: Successful POST requests
- **204 No Content**: Successful DELETE requests
- **404 Not Found**: Resource not found

## Technologies Used

- Spring Boot 3.2.0
- Spring Web
- Java 17
- Maven

## Author

Student ID: 26965
Name: Irakoze Micomyiza Rodrigue
