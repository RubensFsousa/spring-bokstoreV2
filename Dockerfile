# Estágio de construção
FROM 3.9.7-eclipse-temurin-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# Estágio de empacotamento
FROM 24-jdk-slim
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