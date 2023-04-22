FROM openjdk:17-jdk-slim AS build
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} open-weather.jar
ENTRYPOINT ["java", "-jar", "/open-weather.jar"]
