
# Image from OpenJDK 17 binaries built by Eclipse Temurin
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy Maven wrappers and pom file to the container image 
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Resolve all project dependencies 
RUN ./mvnw dependency:go-offline

# Copy src directory 
COPY src ./src

# Compile and run your app w Maven
CMD ["./mvnw", "spring-boot:run"]
