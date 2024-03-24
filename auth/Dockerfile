FROM amazoncorretto:17 AS corretto-jdk

RUN yum update -y && \
    yum install -y binutils && \
    yum clean all

RUN jlink \
     --add-modules ALL-MODULE-PATH \
     --strip-debug \
     --no-man-pages \
     --no-header-files \
     --compress=2 \
     --output /jre

FROM amazonlinux:2

ENV JAVA_HOME=/jre

ENV PATH="${JAVA_HOME}/bin:${PATH}"

COPY --from=corretto-jdk /jre $JAVA_HOME

COPY --from=ghcr.io/ufoscout/docker-compose-wait:latest /wait /wait

EXPOSE 8080
WORKDIR /app
COPY target/*.jar /app/auth.jar
CMD ["java", "-jar", "auth.jar"]
