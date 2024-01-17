FROM amazoncorretto:17-alpine-jdk
COPY target/GatewayService-0.0.1-SNAPSHOT.jar GatewayService-0.0.1-SNAPSHOT.jar
RUN apk update && apk add curl
ENTRYPOINT ["java", "-jar", "GatewayService-0.0.1-SNAPSHOT.jar"]
