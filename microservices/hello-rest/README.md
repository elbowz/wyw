# Spring Boot - Samples - CXF Rest Web Services 

This sample project demonstrates how to use CXF JAX-RS services with Spring Boot. 
This demo has two JAX-RS class resources being auto-discovered and deployed in a single JAX-RS endpoint. 

The application registers itself with Eureka Registry.  

## Testing the server
 
`http://localhost:<port>/sose/helloservice/sayHello/Luigi`

will display "Hello Luigi, Welcome to CXF RS Spring Boot World!!!"

`http://localhost:<port>/sose/helloservice/sayHello2/Luigi`

will display "Hello2 Luigi, Welcome to CXF RS Spring Boot World!!!"

## Swagger JSON and UI

`http://localhost:<port>/sose/helloservice/swagger.json` will return a Swagger JSON description of services.

To view the Swagger document using Swagger-UI, use your Browser to open the Swagger-UI page at:

`http://localhost:<port>/sose/helloservice/api-docs?url=/services/helloservice/swagger.json`

or access it from the CXF Services page:

  `http://localhost:<port>/sose/helloservice/info` 
  
and follow a Swagger link.
> **note**: CXF Services page is available by default at the path ending with `/services`

## System metrics and health checks

`http://localhost:<port>/sose/metrics` 

will expose all registered Spring Actuator metrics 

`http://localhost:<port>/sose/actuator/health`

will expose all registered Spring Actuator health checks