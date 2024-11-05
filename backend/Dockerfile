# Use the official OpenJDK 17 image as a parent image
FROM openjdk:17-jdk

# Set the working directory to /app
WORKDIR /app

# Copy the compiled JAR file into the container at /app
COPY target/Inventory-1.0-SNAPSHOT.jar .

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java","-jar","Inventory-1.0-SNAPSHOT.jar"]
