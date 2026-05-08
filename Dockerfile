# Stage 1: Extract Spring Boot layers
FROM eclipse-temurin:25-jre-alpine as builder
WORKDIR /builder
# Expect the built jar file to be in the target directory
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
# Use Spring Boot's native layertools to extract the jar
RUN java -Djarmode=layertools -jar application.jar extract

# Stage 2: Create the optimized production image
FROM eclipse-temurin:25-jre-alpine
WORKDIR /application

# Best Practice: Run as a non-root user for security
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copy extracted layers from the builder stage in order of least-to-most frequently changed
COPY --from=builder builder/dependencies/ ./
COPY --from=builder builder/spring-boot-loader/ ./
COPY --from=builder builder/snapshot-dependencies/ ./
COPY --from=builder builder/application/ ./

# Expose the default application port
EXPOSE 8080

# Use the Spring Boot launcher (updated class name for Spring Boot 3.2+)
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
