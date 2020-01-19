# Person - REST API

----
### Requirement
Create a REST API which creates, retrieves, updates & deletes Person Entities. 

----

### Solution
 - The requirement has been implemented and exposed as a REST web service.
 - You can verify the implementation through Swagger UI or curl command given below in this guide
 

### Instructions

**Build Project**
```sh
mvn clean package
```

**Generate Test Coverage**
```sh
mvn clean jacoco:prepare-agent package jacoco:report
```

 - Test results will published under /target/site/jacoco/index.html

**Run**
```sh
java -jar target/person-service-1.0.0.jar 
```

**Swagger Documentation**
```sh
http://localhost:9090/swagger-ui.html
```
 - You can test verify the implementation from the Swagger UI given above or using curl command given below 


**Curl Commands**
- Search All Person
```text
curl -X GET --header 'Accept: application/json' 'http://localhost:9090/person/'
```

- Create New Person
```text
curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{"age": "1", "favourite_colour": "blue", "first_name": "zayn", "hobby": _["reading"]_,"last_name": "suhas"}' 'http://localhost:9090/person/'
```

- Delete Person
```text
curl -X DELETE --header 'Accept: text/plain' 'http://localhost:9090/person/2'
```

- Update Person
```text
curl -X PUT --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{"age": "1","favourite_colour": "blue","first_name": "max", "hobby": ["reading"],"last_name": "payne"}' 'http://localhost:9090/person/1'
```

**Database Administration**
 - To manage h2 database, access 
```sh
http://localhost:9090/h2-console
```
 - Make sure that you set JDBC url to jdbc:h2:file:./PersonDB
 
**Building the docker file**
```sh
docker build -t person-service .
```
  
**Running the docker image**
```sh
docker run -p 9090:9090 person-service
```
    