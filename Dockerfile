# Build stage
FROM maven:3.6.0-jdk-17-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# Package stage
FROM adoptium/temurin:17-jre-slim AS runtime
COPY --from=build /home/app/target/*.jar /app/app.jar
ENV TZ 'America/Fortaleza'
RUN echo $TZ > /etc/timezone && \
  apt-get update && apt-get install -y tzdata && \
  apt-get install -y fontconfig libfreetype6 && \
  rm /etc/localtime && \
  ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && \
  dpkg-reconfigure -f noninteractive tzdata && \
  apt-get clean
WORKDIR /app
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

