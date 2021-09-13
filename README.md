# product-review-microservice

![alt text](https://github.com/pyramesh/product-review-microservice/blob/main/architecture.png)

**********************************************
##Technologies used in this assignment
**********************************************
Java 11

Spring Boot 2.X

Spring JPA

Spring Cloud

API Gateway

Spring Security

Circuit Breaker Pattern (Hystrix)

Feign Client

Map Struct

lombok

Maven

Junit5

***********************************************
##Microservices
***********************************************
1. review-service     (port: 8888)
2. product-service      (port: 9999)
3. service-registry     (port: 8761)
4. cloud-gateway        (port: 8080)
5. config-server        (port: 9090)

**************************************************
##how to run the application 
**************************************************

Execute the below commands in the same order.

1. service-registry
   Run the below command in the root directory of service-registry is 'D:\Personal\cybersecurity\service-registry'

`mvn clean install`

Run the application using the below command in the path (.\service-registry\target)

`java -jar service-registry-0.0.1-SNAPSHOT.jar`

2. cloud-gateway
   Run the below command in the root directory of cloud-gateway is 'D:\Personal\cybersecurity\cloud-gateway'

`mvn clean install`

Run the application using the below command in the path (.\cloud-gateway\target)

`java -jar cloud-gateway-0.0.1-SNAPSHOT.jar`

3. config-server
   Run the below command in the root directory of config-server is 'D:\Personal\cybersecurity\config-server'

`mvn clean install`


Run the application using the below command in the path (.\config-server\target)

`java -jar config-server-0.0.1-SNAPSHOT.jar`

4. review-service
   Run the below command in the root directory of review-service is 'D:\Personal\cybersecurity\review-service'

`mvn clean install`

Run the application using the below command in the path (.\service-registry\target)

`java -jar review-service-0.0.1-SNAPSHOT.jar`

5. product-service
   Run the below command in the root directory of product-service is 'D:\Personal\cybersecurity\product-service'

`mvn clean install`

Run the application using the below command in the path (.\product-service\target)

`java -jar product-service-0.0.1-SNAPSHOT.jar`

************************************************
##REST ENDPOINTS
*************************************************

1. create review [`http://localhost:8080/review-service/v1/review`]    
   - Here port **8080** is of **API Gateway** not the review service, all the requests should go through API Gateway
   - this endpoint to create a product resource
   - Since it is a write operation, Authentication is implemented, hence Basic Auth header must be send otherwise will get unauthorized response.
     
     ```
     {
     "productId": "RASS76",
     "averageReviewScore": 501.25,
     "numberOfReviews": 10
     }
      ```
headers:
   ```
   Authorization Basic Auth
   username:client
   password: client
   ```


2. get resource [`[GET] http://localhost:8080/review-service/v1/review/BB5476`] 

This endpoint is to get resource by product ID. Since it is read-only operation, authentication is not applied.

3. delete resource [`[delete] http://localhost:8080/review-service/v1/review/BB5476`] 

This endpoint is to delete resource, Since it is write operation header must pass in order to authnticate.

headers:
   ```
Authorization Basic Auth
username:admin
password: admin
   ```

4. update resource  [`[GET] http://localhost:8888/review-service/v1/review/BB5472`] 

payload:
```
     {
     "averageReviewScore": 501.25,
     "numberOfReviews": 200
     }
```
headers:
   ```
Authorization Basic Auth
username:client
password: client
   ```

## Product service REST end point

get resource [`[GET] http://localhost:8080/product-service/v1/product/BB5476`]
 response : is aggregation of live api+ get review by product id response



************************************************
##improvements
*************************************************
 If I had given time, I would have improved the below items

1. Dockerization 
2. preparing default response if one the services is not responding or down. 
