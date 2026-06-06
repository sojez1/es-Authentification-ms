FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/es-auth-ms.jar es-auth-ms.jar
EXPOSE 8092
ENTRYPOINT ["java", "-jar", "es-auth-ms.jar"]