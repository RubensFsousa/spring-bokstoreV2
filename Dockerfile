# Estágio de construção
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /home/app
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -Dmaven.repo.local=/.m2 -f /home/app/pom.xml package -Dmaven.test.skip=true


# Estágio de empacotamento
FROM openjdk:17-jdk-slim
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