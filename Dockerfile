## BUILDER
FROM gradle:6.9.1-jdk8 AS builder
USER root
COPY src src
COPY build.gradle.kts build.gradle.kts
COPY settings.gradle.kts settings.gradle.kts
RUN mkdir output
RUN gradle test installDist

# RUNNER
FROM openjdk:8u212-jdk-alpine
WORKDIR /home/api
EXPOSE 8080
COPY --from=builder /home/gradle/build/install/java-excercise  /home/api
RUN mkdir output
RUN chmod +x /home/api/bin/java-excercise

ENTRYPOINT ["/home/api/bin/java-excercise"]
