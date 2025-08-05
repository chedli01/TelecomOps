# ===== Stage 1: Build the JAR =====
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# ===== Stage 2: Run the JAR =====
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy the jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Set environment variables for Spring Boot
ENV SPRING_PROFILES_ACTIVE=docker
ENV JAVA_OPTS=""

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
