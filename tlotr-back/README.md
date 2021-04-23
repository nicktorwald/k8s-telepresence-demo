# Getting started with TLOTR backend

Start a PostgreSQL instance on `localhost:5432`.  

Run `./mvnw spring-boot:run` for a dev server. Send requests to `http://localhost:8080/`.

## Build

Run `./mvnw clean install` to build the project. The build artifacts will be stored in the `target/` directory.

## Running unit tests

Run `./mvnw test` to execute the unit tests via [Surefire](https://maven.apache.org/surefire/maven-surefire-plugin/index.html).

## Running integration tests

Run `./mvnw verify` to execute the end-to-end tests via [Failsafe](https://maven.apache.org/surefire/maven-failsafe-plugin/index.html).
