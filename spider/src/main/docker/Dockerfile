#FROM frolvlad/alpine-oraclejdk8:slim
#FROM frolvlad/alpine-oraclejdk8-temporary
FROM java:8
VOLUME /tmp
ADD spider-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8761