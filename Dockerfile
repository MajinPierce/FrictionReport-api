FROM eclipse-temurin:17-jre-alpine
COPY target/*.jar /friction-report.jar
ENTRYPOINT ["java","-jar","/friction-report.jar"]
EXPOSE 8080