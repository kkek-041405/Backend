# Step 1: Build Stage (Uses a Maven image with JDK 25)
FROM maven:3.9-eclipse-temurin-25 AS build
WORKDIR /app
COPY . .
# We use the built-in Maven tool to compile your Java 25 code
RUN mvn clean package -DskipTests

# Step 2: Run Stage (Uses the official lightweight Java 25 Runtime Environment)
FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
