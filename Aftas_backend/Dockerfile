FROM eclipse-temurin:17-jdk-alpine
MAINTAINER "karimelaoumari"
ADD target/Aftas_backend-0.0.1-SNAPSHOT.jar aftas_backend_v1.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","aftas_backend_v1.jar", "--spring.profiles.active=prod"]

