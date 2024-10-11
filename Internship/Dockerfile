# Use a base image with Java 20
FROM eclipse-temurin:20-jdk AS builder

# Set the working directory
WORKDIR /app

# Copy Gradle wrapper and configuration files
COPY gradlew .
COPY gradle ./gradle

# Make the gradlew script executable
RUN chmod +x ./gradlew

# Copy build files
COPY build.gradle settings.gradle ./

# Download dependencies
RUN ./gradlew dependencies --no-daemon

# Copy the source code
COPY src ./src

# Build the application
RUN ./gradlew bootJar --no-daemon

# Step 2: Run Stage
FROM eclipse-temurin:20-jre

# Set the working directory
WORKDIR /app

# Copy the built jar file from the build stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose port 8080 (default port for Spring Boot)
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
