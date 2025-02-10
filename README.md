# Project-Management

## Microservices Architecture

## Overview
This project demonstrates a microservices architecture designed to manage different services such as Manager, Employee, and Project services. It leverages several core components to ensure scalability, flexibility, and ease of maintenance.

### Core Components
- **Service Registry and Discovery (Eureka Server)**: Allows microservices to register themselves and discover other services.
- **Config Server**: Centralized configuration management for all microservices.
- **Manager Service**: Handles operations related to managers.
- **Employee Service**: Manages employee-related operations.
- **Project Service**: Manages project-related operations.
- **API Gateway**: Acts as a single entry point for all microservices, handling routing and load balancing.

### Microservices Enhancements
- **Validation**: Ensures data integrity and correctness.
- **JUnit Testing**: Unit tests for verifying the functionality of the services.
- **Exception Handling**: Global exception handling for consistent error responses.
- **Aspect-Oriented Programming (AOP)**: Modularizes cross-cutting concerns like logging and security.

## Getting Started

### Prerequisites
- Java 11 or higher
- Maven
- Docker (optional, for containerization)

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/your-repo-name.git

2. Navigate to the project directory:
   ```bash
cd your-repo-name

3. Build the project using Maven:
   ```bash
mvn clean install

### Running the Services

1. Start the Eureka Server:
   ```bash
cd eureka-server
mvn spring-boot:run

2. Start the Config Server:
   ```bash
cd config-server
mvn spring-boot:run

3. Start the Manager, Employee, and Project Services:
   ```bash
cd manager-service
mvn spring-boot:run

   ```bash
cd employee-service
mvn spring-boot:run

   ```bash
cd project-service
mvn spring-boot:run

4. Start the API Gateway:
   ```bash
cd api-gateway
mvn spring-boot:run
