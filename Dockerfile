# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/ASN2-0.0.1-SNAPSHOT.jar ASN2.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ASN2.jar"]
