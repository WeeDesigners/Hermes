FROM openjdk:17
ARG JAR_FILE
COPY ./target /opt
WORKDIR /opt
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "./${JAR_FILE}"]