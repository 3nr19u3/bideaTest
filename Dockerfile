FROM openjdk:17-jdk-alpine
COPY target/booking-service-0.0.1-SNAPSHOT.jar java-app.jar

LABEL authors="kike"

ENTRYPOINT ["java", "-jar", "java-app.jar"]