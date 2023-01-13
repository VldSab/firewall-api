FROM openjdk:17-alpine
ADD /build/libs/backend-api-0.0.1-SNAPSHOT.jar backend-api.jar
ENTRYPOINT ["java", "-jar", "backend-api.jar"]
