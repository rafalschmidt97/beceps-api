FROM adoptopenjdk/openjdk11-openj9:jdk-11.0.1.13-alpine-slim AS build
WORKDIR /app
COPY . .
RUN ./gradlew assemble --no-daemon

FROM openjdk:11-jre-slim AS runtime
COPY --from=build /app/build/libs/*-all.jar /app/app.jar
EXPOSE 80
ENTRYPOINT ["sh", "-c", "java -Dcom.sun.management.jmxremote -noverify ${JAVA_OPTS} -DSERVICE_PORT=80 -jar /app/app.jar"]
