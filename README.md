#  Market Data API - Inditex Technical Test

##  Description
This project is a Spring Boot application developed as a technical test for **Inditex**. It provides a REST API to query the applicable price for a product and a brand on a given date, considering the priority rules defined for the configured price rates.

It has been designed following the principles of **Hexagonal Architecture (Ports and Adapters)**, with a clear separation between domain, application, and infrastructure concerns to ensure flexibility and maintainability.

---

##  Architecture
The application is organized into the following core layers:

- **domain**: Contains the domain entities and output ports (repository interfaces). It represents the core of the application and has no dependencies on infrastructure frameworks.
- **application**: Contains the use cases (input ports) and DTOs. It coordinates the business flow of the application.
- **infrastructure**: Implements the adapters that allow communication with the outside world.
    - `controller`: REST input adapters with Spring MVC and OpenAPI documentation.
    - `repository`: Output adapters for persistence using Spring Data JPA on an **H2** database.
    - `config`: Programmatic configuration of Spring beans and technical documentation.

---

## Project Structure
```text
src/main/java/com/inditex/marketdata
│   MarketDataApplication.java
│
├── application/                
│   ├── dto/
│   │   └── PriceResponse.java
│   └── usecase/
│       ├── GetPriceUseCase.java
│       └── GetPriceUseCaseImpl.java
│
├── domain/                     
│   ├── model/
│   │   └── Price.java
│   └── port/
│       └── PriceRepositoryPort.java
│
└── infrastructure/             
    ├── config/                 
    │   ├── OpenApiConfig.java
    │   └── PriceConfig.java
    ├── controller/             
    │   ├── PriceController.java
    │   └── mapper/
    │       └── PriceResponseMapper.java
    └── repository/             
        ├── JpaPriceRepository.java
        ├── PriceEntity.java
        ├── PriceRepositoryAdapter.java
        └── mapper/
            └── PriceEntityMapper.java
```

---

##  Technological Stack
- **Java 21**: Modern language features such as records and improved type inference.
- **Spring Boot 3.2.3**: Main framework used to build and run the REST API.
- **Spring Data JPA**: Abstraction for the data access layer.
- **H2 Database**: In-memory database used for local runtime and testing purposes.
- **Lombok**: Library used to reduce boilerplate code in infrastructure and configuration layers.
- **Maven**: Dependency management and build lifecycle tool.
- **Spring Boot Starter Test (JUnit 5, MockMvc, Mockito support)**: Essential tools for unit and integration testing.
- **JaCoCo**: Generation of detailed code coverage reports, showing line and branch coverage for the project.
- **SpringDoc OpenAPI 2.5.0 (Swagger)**: Auto-generated and interactive API documentation.

---

##  How to Run the Project

### Prerequisites
- JDK **21** installed and configured.
- Maven installed (you can use the command directly if you have it in your PATH).

### Build and package the application:
1. Clone the repository.
2. Compile, run tests, and package the application:
   ```bash
   mvn clean install
   ```

### Run the application:
```bash
mvn spring-boot:run
```
The application will be running at `http://localhost:8080`.

---

##  API Documentation (Swagger)
You can explore the API visually and perform interactive requests from your browser:

- **Swagger UI**: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- **OpenAPI Doc (JSON)**: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---

##  Test Cases (Example Endpoints)
The main service for querying prices is located at `GET /api/v1/prices`.

**Mandatory Parameters (Query Params):**
- `applicationDate`: Application date in ISO format (e.g., `2020-06-14T10:00:00`).
- `productId`: Product identifier (example: `35455`).
- `brandId`: Brand identifier (example: `1`, corresponding to ZARA).

**Response Behavior:**
- If an applicable price is found, the API returns `200 OK` with the price details.
- If no applicable price is found for the given input parameters, the API returns `404 Not Found`.

### Example Response Payload:
```json
{
  "productId": 35455,
  "brandId": 1,
  "priceList": 1,
  "startDate": "2020-06-14T00:00:00",
  "endDate": "2020-12-31T23:59:59",
  "price": 35.50,
  "curr": "EUR"
}
```

### `curl` Commands for the 5 Test Cases from the requirement:

1. **Test 1: Request at 10:00 on the 14th (Product 35455, Brand 1)**
   ```bash
   curl -X GET "http://localhost:8080/api/v1/prices?applicationDate=2020-06-14T10:00:00&productId=35455&brandId=1"
   ```

2. **Test 2: Request at 16:00 on the 14th (Product 35455, Brand 1)**
   ```bash
   curl -X GET "http://localhost:8080/api/v1/prices?applicationDate=2020-06-14T16:00:00&productId=35455&brandId=1"
   ```

3. **Test 3: Request at 21:00 on the 14th (Product 35455, Brand 1)**
   ```bash
   curl -X GET "http://localhost:8080/api/v1/prices?applicationDate=2020-06-14T21:00:00&productId=35455&brandId=1"
   ```

4. **Test 4: Request at 10:00 on the 15th (Product 35455, Brand 1)**
   ```bash
   curl -X GET "http://localhost:8080/api/v1/prices?applicationDate=2020-06-15T10:00:00&productId=35455&brandId=1"
   ```

5. **Test 5: Request at 21:00 on the 16th (Product 35455, Brand 1)**
   ```bash
   curl -X GET "http://localhost:8080/api/v1/prices?applicationDate=2020-06-16T21:00:00&productId=35455&brandId=1"
   ```

---

##  Testing and Coverage
To execute the complete test suite:
```bash
mvn test
```

### Coverage Report (JaCoCo)
After executing the tests, you can view the code coverage level by opening the following file in your browser:
`target/site/jacoco/index.html`

The report shows line and branch coverage for the project.

---

##  H2 Console
During runtime, you can access the H2 console to check the status of the tables:
- **URL**: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- **JDBC URL**: `jdbc:h2:mem:marketdb`
- **User**: `sa`
- **Password**: *(leave blank)*
