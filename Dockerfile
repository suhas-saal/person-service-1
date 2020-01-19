# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="suhaz786@gmail.com"


# Make port 9090 available to the world outside this container
EXPOSE 9090

# The application's war file
ARG WAR_FILE=target/person-service-1.0.0.jar

# Add the application's war to the container
ADD ${WAR_FILE} person-service.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/person-service.jar"]