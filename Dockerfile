FROM openjdk:17-jdk-slim AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvn dependency:resolve

COPY src src
RUN ./mvn package

FROM openjdk:17-jdk-slim
WORKDIR open-weather
COPY --from=build target/*.jar open-weather.jar
ENTRYPOINT ["java", "-jar", "open-weather.jar"]