<<<<<<< HEAD
# Employee Management System - Spring Boot

## Overview
This is a simple Employee Management System built using the Spring Boot framework. It provides CRUD operations for managing employee records.

## Features
- **Create:** Add new employees with details such as name, phone, address, email, salary, etc.
- **Read:** Retrieve employee details by ID, phone, email, name, etc.
- **Update:** Modify employee information, including phone, email, and salary.
- **Delete:** Remove employees from the system.

## Technologies Used
- Spring Boot
- Spring Data JPA
- MySQL
- Maven

## Getting Started
1. Clone the repository: `git clone https://github.com/Saurabh7deokate/Employee_mgm_system-Spring-Boot-.git`
2. Navigate to the project directory: `cd employee-mgm-system`
3. Configure the database connection in `src/main/resources/application.properties`.
4. Run the application: `mvn spring-boot:run`
5. Access the application at [http://localhost:8080](http://localhost:8080)

## API Endpoints
- **Create Employee:** `POST /save`
- **Read Employee:** `GET /fetch/id={employeeId}`
- **Update Employee:** `PUT /update/id={employeeId}`
- **Delete Employee:** `DELETE /delete/{id}`
- **Get All Employees:** `GET /fetchAll`

## Usage Examples
```bash
# Create Employee
curl -X POST -H "Content-Type: application/json" -d '{"name": "Unnati", "phone": "1234567890", "email": "unnati@example.com", "salary": 50000.0, "address": "Pune"}' http://localhost:8080/save

# Get Employee by ID
curl http://localhost:8080/fetch/id=1

# Update Employee
curl -X PUT -H "Content-Type: application/json" -d '{"name": "Unnati", "phone": "1234567891", "email": "unnatioak@example.com", "salary": 59000.0, "address": "Mumbai"}' http://localhost:8080/update/id=1

# Delete Employee
curl -X DELETE http://localhost:8080/delete/1

# Get All Employees
curl http://localhost:8080/fetchAll

-- Contributions are welcome! If you find a bug or have a feature request, please open an issue.
=======

>>>>>>> responsestructure
