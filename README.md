
# TaskDo App

### Overview
TaskDo App is a task management system that leverages Spring Boot, MySQL, Lombok, and JWT for building a secure and scalable backend. 
The application features role-based authorization, allowing users and admins to manage tasks efficiently.

### Technologies Used
- **Spring Boot**: Backend framework.
- **MySQL**: Relational database.
- **Lombok**: Reduces boilerplate code.
- **JWT (JSON Web Token)**: Secure authentication and authorization.

---

## API Endpoints

### 1. User Registration
- **Method**: `POST`
- **Endpoint**: `/api/auth/user/register`
- **Description**: Registers a new user.

### 2. Admin Registration
- **Method**: `POST`
- **Endpoint**: `/api/auth/admin/register`
- **Description**: Registers a new admin.

### 3. Login
- **Method**: `POST`
- **Endpoint**: `/api/auth/login`
- **Description**: Logs in both users and admins.

### 4. Assign Task to User
- **Method**: `POST`
- **Endpoint**: `/api/admin/{user_id}/tasks`
- **Description**: Admin assigns a task to a user. Accessible only by admins.

### 5. Get All Tasks by User ID
- **Method**: `GET`
- **Endpoint**: `/api/user/{user_id}/tasks`
- **Description**: Retrieves all tasks assigned to a user. Accessible only by users.

### 6. Get Individual Task
- **Method**: `GET`
- **Endpoint**: `/api/user/{user_id}/tasks/{task_id}`
- **Description**: Retrieves a specific task assigned to a user. Accessible only by users.

### 7. Delete Task
- **Method**: `DELETE`
- **Endpoint**: `/api/admin/{user_id}/tasks/{task_id}`
- **Description**: Deletes a specific task assigned to a user. Accessible only by admins.

---

## Entity Models

### User
- **Fields**: 
  - `id`
  - `name`
  - `email` (unique)
  - `password` (encoded)
  - `role` (User/Admin)

### Task
- **Fields**: 
  - `id`
  - `taskname`
  - Many-to-One mapping to `User`

---

## Package Structure

### 1. `org.jsp.TaskApp`
- **TaskAppApplication.java**: Main application class.

### 2. `org.jsp.TaskApp.controller`
- **AuthController.java**: Handles authentication APIs (1, 2, 3).
- **TaskController.java**: Handles task-related APIs (4, 5, 6, 7).

### 3. `org.jsp.TaskApp.entity`
- **Role.java**: Enum for roles.
- **Task.java**: Task entity.
- **User.java**: User entity.

### 4. `org.jsp.TaskApp.exceptions`
- **ApiException.java**: Custom API exception.
- **TaskNotFound.java**: Exception for task not found.
- **UserNotFound.java**: Exception for user not found.

### 5. `org.jsp.TaskApp.payload`
- **JWTAuthResponse.java**: Response payload for JWT authentication.
- **LoginDto.java**: Data transfer object for login.
- **TaskDto.java**: Data transfer object for tasks.
- **UserDto.java**: Data transfer object for users.

### 6. `org.jsp.TaskApp.repository`
- **TaskRepository.java**: Repository for task entity.
- **UserRepository.java**: Repository for user entity.

### 7. `org.jsp.TaskApp.security`
- **CustomUserDetailsService.java**: Custom user details service.
- **JWTAuthFilter.java**: JWT authentication filter.
- **JWTTokenProvider.java**: JWT token provider.

### 8. `org.jsp.TaskApp.security.config`
- **SecurityConfig.java**: Security configuration with beans for `SecurityFilterChain`, `PasswordEncoder`, and `AuthenticationManager`.

### 9. `org.jsp.TaskApp.service`
- **TaskService.java**: Service interface for tasks.
- **UserService.java**: Service interface for users.

### 10. `org.jsp.TaskApp.service.impl`
- **TaskServiceImpl.java**: Implementation of `TaskService`.
- **UserServiceImpl.java**: Implementation of `UserService`.

---

## Testing
- **Tool**: Postman
- **Description**: Used to test all APIs.

---

## How to Run the Project

1. **Clone the repository**:
   ```bash
   git clone https://github.com/Prateeksrdpc/TaskApp.git
   ```

2. **Navigate to the project directory**:
   ```bash
   cd TaskApp
   ```

3. **Configure the database settings** in `application.properties`.

4. **Build and run the application**:
   ```bash
   ./mvnw spring-boot:run
   ```

5. **Access the APIs**:
   - Base URL: `http://localhost:9090`

---
