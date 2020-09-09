FROM openjdk:14-jdk-alpine
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=docker","/app.jar"]