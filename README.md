```markdown
# Test Task for Junior Java Developer

## Overview

This task involves implementing an HTTP API server for managing `Customer` entities. The server should support basic CRUD operations and be developed using Spring Boot. No frontend is required, and authentication is not necessary.

## Requirements

- **Backend Framework**: Spring Boot
- **Database**: Any relational SQL database (MySQL, PostgreSQL, etc.)
- **Authentication**: Not required

## Endpoints

### 1. Create Customer

- **Method**: POST
- **Endpoint**: `/api/customers`
- **Content-Type**: `application/json`
- **Request Body**:
  ```json
  {
    "fullName": "String (2..50 chars including whitespaces)",
    "email": "String (2..100 chars, unique, should include exactly one @)",
    "phone": "String (6..14 chars, only digits, should start from +, optional field)"
  }
  ```
- **Response Body**:
  ```json
  {
    "id": "Long",
    "fullName": "String",
    "email": "String",
    "phone": "String"
  }
  ```

### 2. Read All Customers

- **Method**: GET
- **Endpoint**: `/api/customers`
- **Response Body**:
  ```json
  [
    {
      "id": "Long",
      "fullName": "String",
      "email": "String",
      "phone": "String"
    }
  ]
  ```

### 3. Read Customer

- **Method**: GET
- **Endpoint**: `/api/customers/{id}`
- **Response Body**:
  ```json
  {
    "id": "Long",
    "fullName": "String",
    "email": "String",
    "phone": "String"
  }
  ```

### 4. Update Customer

- **Method**: PUT
- **Endpoint**: `/api/customers/{id}`
- **Content-Type**: `application/json`
- **Request Body**:
  ```json
  {
    "id": "Long",
    "fullName": "String (2..50 chars including whitespaces)",
    "email": "String (not editable)",
    "phone": "String (6..14 chars, only digits, should start from +)"
  }
  ```
- **Response Body**:
  ```json
  {
    "id": "Long",
    "fullName": "String",
    "email": "String",
    "phone": "String"
  }
  ```

### 5. Delete Customer

- **Method**: DELETE
- **Endpoint**: `/api/customers/{id}`
- **Description**: Marks the customer as deleted by setting the `is_active` field to false, but does not remove the data from the database.

## Database Structure

### `customer` Table

- `id` (bigint)
- `created` (bigint)
- `updated` (bigint)
- `full_name` (varchar)
- `email` (varchar)
- `phone` (varchar, nullable)
- `is_active` (bool)
