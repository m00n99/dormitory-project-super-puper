FROM eclipse-temurin:19-alpine
VOLUME /tmp
COPY build/libs/miracle-workers-peristence-api-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]