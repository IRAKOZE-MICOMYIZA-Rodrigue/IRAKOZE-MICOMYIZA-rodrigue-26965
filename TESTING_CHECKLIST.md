# Testing Checklist

Use this checklist to verify all endpoints are working correctly.

## Question 1: Library Book Management API

- [ ] GET /api/books - Returns 3 books
- [ ] GET /api/books/1 - Returns specific book
- [ ] GET /api/books/999 - Returns 404 Not Found
- [ ] GET /api/books/search?title=clean - Returns Clean Code book
- [ ] POST /api/books - Creates new book, returns 201
- [ ] DELETE /api/books/1 - Deletes book, returns 204
- [ ] DELETE /api/books/999 - Returns 404 Not Found

## Question 2: Student Registration API

- [ ] GET /api/students - Returns 5 students
- [ ] GET /api/students/1 - Returns specific student
- [ ] GET /api/students/999 - Returns 404 Not Found
- [ ] GET /api/students/major/Computer Science - Returns 3 students
- [ ] GET /api/students/filter?gpa=3.5 - Returns students with GPA >= 3.5
- [ ] POST /api/students - Creates new student, returns 201
- [ ] PUT /api/students/1 - Updates student, returns 200
- [ ] PUT /api/students/999 - Returns 404 Not Found

## Question 3: Restaurant Menu API

- [ ] GET /api/menu - Returns 8 menu items
- [ ] GET /api/menu/1 - Returns specific menu item
- [ ] GET /api/menu/999 - Returns 404 Not Found
- [ ] GET /api/menu/category/Appetizer - Returns appetizers
- [ ] GET /api/menu/category/Main Course - Returns main courses
- [ ] GET /api/menu/available?available=true - Returns available items
- [ ] GET /api/menu/available?available=false - Returns unavailable items
- [ ] GET /api/menu/search?name=chicken - Returns items with "chicken"
- [ ] POST /api/menu - Creates new menu item, returns 201
- [ ] PUT /api/menu/1/availability - Toggles availability
- [ ] DELETE /api/menu/1 - Deletes menu item, returns 204

## Question 4: E-Commerce Product API

- [ ] GET /api/products - Returns 10 products
- [ ] GET /api/products?page=0&limit=5 - Returns first 5 products
- [ ] GET /api/products?page=1&limit=5 - Returns next 5 products
- [ ] GET /api/products/1 - Returns specific product
- [ ] GET /api/products/999 - Returns 404 Not Found
- [ ] GET /api/products/category/Electronics - Returns electronics
- [ ] GET /api/products/brand/Apple - Returns Apple products
- [ ] GET /api/products/search?keyword=phone - Returns products with "phone"
- [ ] GET /api/products/price-range?min=100&max=500 - Returns products in range
- [ ] GET /api/products/in-stock - Returns 9 products (excludes out of stock)
- [ ] POST /api/products - Creates new product, returns 201
- [ ] PUT /api/products/1 - Updates product, returns 200
- [ ] PATCH /api/products/1/stock?quantity=100 - Updates stock
- [ ] DELETE /api/products/1 - Deletes product, returns 204

## Question 5: Task Management API

- [ ] GET /api/tasks - Returns 5 tasks
- [ ] GET /api/tasks/1 - Returns specific task
- [ ] GET /api/tasks/999 - Returns 404 Not Found
- [ ] GET /api/tasks/status?completed=true - Returns completed tasks
- [ ] GET /api/tasks/status?completed=false - Returns incomplete tasks
- [ ] GET /api/tasks/priority/HIGH - Returns high priority tasks
- [ ] GET /api/tasks/priority/MEDIUM - Returns medium priority tasks
- [ ] GET /api/tasks/priority/LOW - Returns low priority tasks
- [ ] POST /api/tasks - Creates new task, returns 201
- [ ] PUT /api/tasks/1 - Updates task, returns 200
- [ ] PATCH /api/tasks/1/complete - Marks task as completed
- [ ] DELETE /api/tasks/1 - Deletes task, returns 204

## Bonus: User Profile API

- [ ] GET /api/users - Returns wrapped response with 3 users
- [ ] GET /api/users/1 - Returns wrapped response with user
- [ ] GET /api/users/999 - Returns wrapped error response
- [ ] GET /api/users/search/username?username=john - Returns matching users
- [ ] GET /api/users/search/country?country=USA - Returns users from USA
- [ ] GET /api/users/search/age-range?min=25&max=35 - Returns users in range
- [ ] POST /api/users - Creates user, returns wrapped response with 201
- [ ] PUT /api/users/1 - Updates user, returns wrapped response
- [ ] PATCH /api/users/3/activate - Activates user (was inactive)
- [ ] PATCH /api/users/1/deactivate - Deactivates user
- [ ] DELETE /api/users/1 - Deletes user, returns wrapped response

## Response Format Verification

### Standard Responses (Questions 1-5)
- [ ] Successful GET returns JSON object/array
- [ ] Successful POST returns 201 with created resource
- [ ] Successful PUT returns 200 with updated resource
- [ ] Successful PATCH returns 200 with updated resource
- [ ] Successful DELETE returns 204 with no content
- [ ] Not found returns 404

### Wrapped Responses (Bonus Question)
- [ ] All responses have "success" field (boolean)
- [ ] All responses have "message" field (string)
- [ ] All responses have "data" field (object/array/null)
- [ ] Success responses have success=true
- [ ] Error responses have success=false

## Browser Testing (GET endpoints only)

Quick test in browser:
- [ ] http://localhost:8081/api/books
- [ ] http://localhost:8081/api/students
- [ ] http://localhost:8081/api/menu
- [ ] http://localhost:8081/api/products
- [ ] http://localhost:8081/api/tasks
- [ ] http://localhost:8081/api/users

## Postman Testing

- [ ] Import Postman_Collection.json
- [ ] Test all Question 1 endpoints
- [ ] Test all Question 2 endpoints
- [ ] Test all Question 3 endpoints
- [ ] Test all Question 4 endpoints
- [ ] Test all Question 5 endpoints
- [ ] Test all Bonus Question endpoints

## Code Quality Checks

- [ ] All classes have proper package declarations
- [ ] All imports are correct
- [ ] No compilation errors
- [ ] Proper indentation throughout
- [ ] Meaningful variable names
- [ ] Proper Java naming conventions (camelCase, PascalCase)
- [ ] All controllers have @RestController annotation
- [ ] All endpoints have proper HTTP method annotations
- [ ] All path variables use @PathVariable
- [ ] All query parameters use @RequestParam
- [ ] All request bodies use @RequestBody

## Documentation Checks

- [ ] README.md is complete
- [ ] All endpoints documented in README
- [ ] Sample requests/responses provided
- [ ] QUICK_START.md is clear and helpful
- [ ] Postman collection is valid JSON
- [ ] PROJECT_SUMMARY.md is accurate

## Final Verification

- [ ] Application starts without errors
- [ ] Application runs on port 8080
- [ ] All 55+ endpoints are accessible
- [ ] Sample data is pre-loaded
- [ ] All CRUD operations work
- [ ] All search/filter operations work
- [ ] All HTTP status codes are correct
- [ ] Ready for submission

---

**Testing Status**: _____ / 100+ checks completed

**Notes**:
_______________________________________
_______________________________________
_______________________________________
