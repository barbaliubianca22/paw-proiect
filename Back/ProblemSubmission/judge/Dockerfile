FROM alpine:latest
RUN apk update
RUN apk add build-base
RUN apk fetch openjdk17
RUN apk add openjdk17
RUN apk add gcompat
RUN adduser --no-create-home --disabled-password dummy
RUN apk add bash

ENV JAVA_HOME=/usr/lib/jvm/java-1.17.0-openjdk
ENV PATH="$JAVA_HOME/bin:${PATH}"

WORKDIR usr/src/app

COPY target/judge-0.0.1-SNAPSHOT.jar judge-0.0.1-SNAPSHOT.jar
COPY runner runner
ENTRYPOINT ["java", "-jar", "judge-0.0.1-SNAPSHOT.jar"]
