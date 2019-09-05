FROM gradle:jdk10 as builder
COPY --chown=gradle:gradle . /conventionAPI
WORKDIR /conventionAPI
RUN gradle bootJar

FROM openjdk:8-jdk-alpine
EXPOSE 8080
VOLUME /tmp
ARG LIBS=conventionAPI/build/libs
COPY --from=builder ${LIBS}/ /conventionAPI/lib
ENTRYPOINT ["java","-jar","/conventionAPI/lib/spring-boot-jpa-0.0.1-SNAPSHOT.jar"]
