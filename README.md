# Randoli Events API

Code repository for the Events API assignment.

## Running locally

---
**NOTE**

PostgreSQL must be running in the local environment.

---

1. Checkout the `master` branch.
2. In the `application.properties` file, update the values for `spring.datasource.url`, `spring.datasource.username`, and `spring.datasource.password` with your local postgres values.  
3. Run `mvn clean install` from the project root directory to package the bundle.
4. Run `mvn spring-boot:run` to run the application.
