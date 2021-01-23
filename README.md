# Randoli Events API

Code repository for the Events API assignment.

## Running locally

---
**Prerequisite**

PostgreSQL must be running in the local environment.

---

1. Checkout the `master` branch.
2. In the `application.properties` file, update the values for `spring.datasource.url`, `spring.datasource.username`, and `spring.datasource.password` with your local postgres values.  
3. Run `mvn clean install` from the project root directory to package the bundle.
4. Run `mvn spring-boot:run` to run the application.

#### API URL: `http://localhost:8080/api`
#### Swagger URL: `http://localhost:8080/swagger-ui`

## Kubernetes deployment

---
**Prerequisite**

A Kubernetes cluster must be setup and kubectl must be configured with it.

---

1. Checkout `master` branch.
2. Change directory to `devops`.
3. Run `deploy.sh` script.

#### API URL: `http://<kubernetes cluster IP>:30080/api`
#### Swagger URL: `http://<kubernetes cluster IP>:30080/swagger-ui`