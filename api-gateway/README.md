# API Gateway

Spring Cloud Gateway implementation for the Book Store Microservices project.

## Features

- Routes requests to appropriate microservices
- CORS configuration
- Circuit breaker patterns with fallbacks
- Environment-based configuration

## Environment Variables

The following environment variables can be configured:

| Variable | Description | Default Value |
|----------|-------------|---------------|
| CATALOG_SERVICE_URL | URL of the Catalog Service | http://localhost:18081 |
| ORDER_SERVICE_URL | URL of the Order Service | http://localhost:18082 |

## Running the Application

### Using Maven
