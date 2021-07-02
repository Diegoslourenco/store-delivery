# Delivery store RESTful API

Application built in order to practice and apply the REST architetural concepts.

## Dependencies: 

- [Spring Boot 2.4.5](https://spring.io/projects/spring-boot)
- [Spring Security](https://spring.io/projects/spring-security)
- [Spring HATEOAS](https://spring.io/projects/spring-hateoas)
- [flyway](https://flywaydb.org/)
- [JWT - JSON Web Token 0.9.1](https://www.jsonwebtoken.io/)

## Documentation

There is a Postman Collection inside the folder postman_collection.
Also, it can be accessed online [here](https://documenter.getpostman.com/view/13054024/TzY3BbYM).

## Internal Architecture
The internal architecture:

  * `Controllers` classes provide endpoints and deal with HTTP requests and responses from/to the client in JSON format
  * `Service` 	classes contains the business rules, receive the request from a controller and talk to repository to acess data to return a response to controller
  * `Repository` classes interface with the DAO internally that interface with the database and take care of writing and reading data to/from persistent storage 
