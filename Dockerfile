FROM gradle:jdk23 AS build
COPY --chown=gradle:gradle . /app
WORKDIR /app
RUN gradle build --no-daemon

FROM eclipse-temurin:23-jdk
COPY --from=build /app/build/libs/${APP_NAME}-1.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]