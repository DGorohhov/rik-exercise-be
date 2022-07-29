# RIK's exercise BE

Microservice which provides event data management according to RIK exercise requirement's.

## Overall info

Service is a Spring Boot application. 
Service consists of following stack:

- spring boot 2
- java 17
- lombok
- mapstruct
- h2


## Run application

Spring Boot project can be run directly from [RikExerciseBeApplication.main()](src/main/java/eu/gordaniil/rikexercisebe/RikExerciseBeApplication.java)
method or via executable jar file. More info on running Spring applications:
[running your application.](https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-running-your-application.html)

## Instructions

To compile (also runs unit tests)

```
mvn package
```

## To run the webapp manually

```
mvn spring-boot:run
```

## To run integration tests

```
mvn spring-boot:run
mvn verify
```


## Project structure

```
rik-exercise-be
   |-- pom.xml
   |-- main
        |-- java.java.eu.gordaniil.rikexercisebe
            |-- aop -> aspect layer for logging and exception handling
            |-- config -> application configs
            |-- constant -> application constants
            |-- controller -> controller layer, implementation of the api, validation, mapping from API - Model to Domain-Model
            |-- domain -> domain layer for persistence, defining entities, repositories and business logic
            |-- error -> exception classes and error response model
            |-- helper -> application helper methods (are mainly useful when FE has different users, in this case there are no users)
            |-- log -> application logging model
            |-- validation -> application validation constraint
            |-- RikExerciseBeApplication -> application layer, having the typical main class as application entrypoint
        |-- resources -> application environment & log configs
    |-- test
        |-- java.java.eu.gordaniil.rikexercisebe
            |-- controller -> application controller tests
            |-- domain -> application validation & business logic tests
```
