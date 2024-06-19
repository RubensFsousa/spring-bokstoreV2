# Build stage
FROM maven:3.8.4-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# Package stage
FROM openjdk:17-oracle
COPY --from=build /home/app/target/*.jar /app/app.jar
ENV TZ 'America/Fortaleza'
WORKDIR /app
ENTRYPOINT ["java", "-jar", "/app/app.jar"]