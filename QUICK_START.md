# Quick Start Guide

## Prerequisites
- Java 17 or higher
- Maven 3.6+
- Postman (for testing)

## Running the Application

### Option 1: Using Maven
```bash
cd restful-api-project
mvn spring-boot:run
```

### Option 2: Using IDE
1. Open the project in your IDE (IntelliJ IDEA, Eclipse, VS Code)
2. Run `RestfulApiApplication.java`

The application will start on **http://localhost:8081**

## Quick Testing Guide

### Using Browser (GET requests only)
- Books: http://localhost:8081/api/books
- Students: http://localhost:8081/api/students
- Menu: http://localhost:8081/api/menu
- Products: http://localhost:8081/api/products
- Tasks: http://localhost:8081/api/tasks
- Users: http://localhost:8081/api/users

### Using Postman
1. Import `Postman_Collection.json` into Postman
2. All endpoints are pre-configured
3. Test each endpoint by clicking "Send"

## Sample Test Scenarios

### Question 1: Library Books
1. GET all books → Should return 3 books
2. Search for "clean" → Should find "Clean Code"
3. POST new book → Should return 201 Created
4. DELETE book with ID 1 → Should return 204 No Content

### Question 2: Students
1. GET all students → Should return 5 students
2. Filter by major "Computer Science" → Should return 3 students
3. Filter by GPA >= 3.5 → Should return 3 students
4. POST new student → Should return 201 Created

### Question 3: Restaurant Menu
1. GET all menu items → Should return 8 items
2. GET by category "Appetizer" → Should return 2 items
3. GET available items → Should return 7 items
4. Toggle availability for item 1 → Should change status

### Question 4: E-Commerce Products
1. GET all products → Should return 10 products
2. GET with pagination ?page=0&limit=5 → Should return 5 products
3. Search by keyword "phone" → Should return 2 products
4. Filter by price range 100-500 → Should return matching products
5. GET in-stock products → Should return 9 products (1 is out of stock)

### Question 5: Tasks
1. GET all tasks → Should return 5 tasks
2. Filter by completed=false → Should return 3 incomplete tasks
3. Filter by priority "HIGH" → Should return 2 tasks
4. PATCH task 1 to complete → Should mark as completed

### Bonus: User Profiles
1. GET all users → Should return wrapped response with success=true
2. Search by username "john" → Should find matching users
3. Search by country "USA" → Should return users from USA
4. Activate/Deactivate user → Should toggle active status

## Common Issues

### Port Already in Use
If port 8080 is already in use, change it in `application.properties`:
```properties
server.port=8081
```

### Maven Build Fails
Make sure you have Java 17 or higher:
```bash
java -version
```

## HTTP Status Codes Reference
- **200 OK**: Successful GET/PUT/PATCH
- **201 Created**: Successful POST
- **204 No Content**: Successful DELETE
- **404 Not Found**: Resource not found

## Submission Checklist
- [ ] All 5 questions implemented
- [ ] Bonus question implemented
- [ ] All endpoints tested
- [ ] README.md completed
- [ ] Code properly formatted
- [ ] Project runs without errors
- [ ] Branch created: restFull_api_StudentId
- [ ] Code pushed to branch
