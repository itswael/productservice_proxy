# Product Service Proxy

A Spring Boot microservice that handles product catalog operations —
listing, fetching, searching, and category browsing — by proxying data from
[FakeStoreAPI](https://fakestoreapi.com/) and supporting search/sort/filter
via OpenSearch. Requests for individual products are gated behind a JWT
issued by the user service.

This service is part of a small e-commerce microservices suite together with
[`userservice`](https://github.com/itswael/userservice) (which issues the
JWTs validated here) and
[`paymentservice`](https://github.com/itswael/paymentservice).

## Features

- CRUD operations on products, proxied through FakeStoreAPI
  (`FakeStoreClient`)
- Category listing and per-category product lookup
- Product search with pagination, sorting, and filtering
  (`SearchService`, `FilterFactory`, `SorterFactory`) backed by OpenSearch
  (`spring-data-opensearch`)
- JWT-based authorization on single-product lookups (`TokenValidator`)
- Pluggable filter/sort strategies (e.g. `BrandFilter`, `RamFilter`,
  `PriceHighToLowSorter`, `PriceLowToHighSorter`)

## API Endpoints

| Method | Path                             | Description                                  |
|--------|-----------------------------------|-----------------------------------------------|
| GET    | `/products`                       | List all products                             |
| GET    | `/products/{productId}`           | Get a single product (requires `Authorization` header / JWT) |
| POST   | `/products`                       | Add a new product                             |
| PUT    | `/products/{productId}`           | Update a product                              |
| DELETE | `/products/{productId}`           | Delete a product                              |
| GET    | `/products/categories`            | List all product categories                   |
| GET    | `/products/category/{categoryName}` | List products in a category                 |
| POST   | `/search`                         | Search products by query, page, and sort params |
| GET    | `/search/`                        | Search with query, filters, sort, and pagination |

## Tech Stack

- Java 17, Spring Boot 3.1.4
- Spring Data JPA, Spring Data OpenSearch
- MySQL (via `mysql-connector-j`) with Flyway migrations
- Apache HttpClient 5 (`RestTemplate` requests to FakeStoreAPI)
- Lombok

## Setup

### Prerequisites

- Java 17
- Maven (or use the bundled `./mvnw`)
- A MySQL instance
- An OpenSearch cluster/endpoint

### Configuration

Datasource and OpenSearch connection details are configured in
`src/main/resources/application.properties`. Set your own MySQL credentials
and `opensearch.uris` before running the service — **do not commit real
credentials or internal endpoint URLs**; use environment variables or a
local, git-ignored override instead.

### Run

```bash
./mvnw spring-boot:run
```

### Test

```bash
./mvnw test
```

## License

Released under the [MIT License](LICENSE).
