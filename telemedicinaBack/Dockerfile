FROM openjdk:21-jdk-slim
LABEL authors="DELL"
ARG JAR_FILE=target/telemedicinaBack-0.0.1.jar
COPY ${JAR_FILE} app_telemedicinaBack.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_telemedicinaBack.jar"]