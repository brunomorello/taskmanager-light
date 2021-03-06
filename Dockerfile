FROM openjdk:12-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Xmx512m", "-Dspring.profiles.active=prod", "-Dserver.port=${PORT}", "-jar", "/app.jar"]