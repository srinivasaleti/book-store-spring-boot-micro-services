# Use a minimal JDK runtime image
FROM eclipse-temurin:21-jre

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/*.jar app.jar

# Expose the application port
EXPOSE 8989

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
