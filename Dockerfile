FROM eclipse-temurin:21-jdk-alpine
COPY /target/*.jar /opt/app/lablogistica.jar
LABEL maintainer="rodolfobortolozo@gmail.com"
LABEL name="lablogistica"
LABEL version="1.0"
ENTRYPOINT ["java","-jar","/opt/app/lablogistica.jar"]
EXPOSE 8080