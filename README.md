# Cloudcase Pay Calculator

Cloudcase Pay Calculator service

## Getting Started

### Prerequisites

### Pull Postgres from the Docker Hub

```
docker pull postgres
```

### Running postgres on localhost

Run postgreSQL

```
docker run --name postgres  -p 5432:5432 -e POSTGRES_PASSWORD=password -d postgres
```

Connect to the database via docker to create both the user and the databases

```
docker run -it --rm --link postgres:postgres postgres psql -h postgres -U postgres
```

### Create the database

```
CREATE DATABASE paycaldb;
```

### Running the instance

```
./gradlew bootRun
```

### Running the tests

```
./gradlew test
```

### Sample POST to construct on Postman

POST http://localhost:8080/taxcalculator

```
{
    "salaryAmount" : "130000",
    "taxYear": "2023"
}
```
