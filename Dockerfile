FROM eclipse-temurin:17-jdk-alpine as build

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw clean install -DskipTests

FROM eclipse-temurin:17-jre-alpine
COPY target/*.jar /friction-report.jar
ENTRYPOINT ["java","-jar","/friction-report.jar"]
EXPOSE 9090