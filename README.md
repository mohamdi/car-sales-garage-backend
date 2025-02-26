# Car Sales Garage API

## Project dependencies
This project is using Java 21, Spring Boot 3.3.4 and H2 in memory database.
To be able to use the H2 database, you need to run the application with the `--spring.profiles.active=local` flag.

## Project structure

The project structure is as follows:

```
.
├── README.md
├── pom.xml
├── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── car_sales_garage
    │   │           └── CarSalesGarageApplication.java
    │   │               └── aop
    │   │               └── config
    │   │               └── controller
    │   │               └── exception
    │   │               └── repository
    │   │               └── service
    │   │               └── model
    │   │                   └── entity
    │   │                   └── dto
    │   │                   └── mapper
    │   │                   └── enumeration
    │   │                
    │   └── resources
    │       └── db
    │           └── changelog
    │               └── db.changelog-master.xml
    │               └── init.sql
    │               └── init-data.sql
    │       └── static
    │           └── index.html
    │       └── application.properties
    │       └── application-local.properties
    └── test
        └── java
            └── com
                └── car_sales_garage
                    └── CarSalesGarageApplicationTests.java
```
