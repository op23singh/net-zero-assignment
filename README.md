# Restaurant Table Management API

A Spring Boot REST API for managing restaurant tables with reservation functionality and optimistic locking to prevent concurrency conflicts.

## Features

- CRUD operations for restaurant tables
- Table reservation system
- Optimistic locking for concurrent modifications
- Input validation with Jakarta Bean Validation
- Global exception handling
- RESTful API design

## Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database (in-memory)
- Jakarta Bean Validation
- Maven

## API Endpoints

| Method | URL                      | Description                    |
|--------|--------------------------|--------------------------------|
| GET    | /api/tables              | Get all tables                 |
| GET    | /api/tables/{id}         | Get table by ID                |
| POST   | /api/tables              | Create a new table             |
| PUT    | /api/tables/{id}         | Update a table                 |
| DELETE | /api/tables/{id}         | Delete a table                 |
| PATCH  | /api/tables/{id}/reserve | Reserve a table                |

## Getting Started

### Prerequisites

- JDK 17+
- Maven 3.6+

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/restaurant-table-management.git
   cd restaurant-table-management

### To run Integration Tests
Use Command : mvn veriy 

### To run unit Tests 
Use Command : mvn test 

### To run the application 
Use Command: mvn spring-boot:run
