# Servlet Assignments

This project contains two servlet assignments:

## Assignment 1: Login Servlet
- Login form with username and password fields
- Password validation (minimum 8 characters)
- Displays appropriate messages based on password strength

## Assignment 2: Send Redirect
- Form with input field for search query
- Redirects to Google search using the input value

## Project Structure
```
src/
├── main/
│   ├── java/
│   │   ├── LoginServlet.java
│   │   └── RedirectServlet.java
│   └── webapp/
│       ├── WEB-INF/
│       │   └── web.xml
│       ├── index.html
│       ├── login.html
│       └── redirect.html
└── pom.xml
```

## How to Run
1. Deploy the WAR file to a servlet container (Tomcat, Jetty, etc.)
2. Access the application at: http://localhost:8080/servlet-assignment/
3. Navigate to either assignment from the main page

## Testing
- **Assignment 1**: Enter username and password, test with passwords < 8 and >= 8 characters
- **Assignment 2**: Enter search terms and verify redirect to Google search

## URLs
- Main page: `/`
- Login form: `/login.html`
- Login servlet: `/login`
- Redirect form: `/redirect.html`
- Redirect servlet: `/redirect`