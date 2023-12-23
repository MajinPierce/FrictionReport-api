FROM maven:3.8-eclipse-temurin-17-alpine as builder

WORKDIR friction-report/
COPY pom.xml .
COPY src src

RUN mvn package

FROM eclipse-temurin:17-jre-alpine

COPY --from=builder friction-report/target/*.jar /friction-report.jar

ENTRYPOINT ["java","-jar","/friction-report.jar"]