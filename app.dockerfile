FROM adoptopenjdk/openjdk11
MAINTAINER Marcelo Garcia Palma

RUN apt-get update && \
    mkdir -p /opt/app

COPY /kotlin-exposed-koin-javalin/agent/jmx_prometheus_javaagent-0.15.0.jar /opt/app
COPY /kotlin-exposed-koin-javalin/agent/config.yaml /opt/app
COPY /kotlin-exposed-koin-javalin/build/libs/register-1.0.0.jar /opt/app/app.jar
ENV DATABASE_JDBC_URL=jdbc:postgresql://register-postgres:5432/postgres
ENV DATABASE_USER_NAME=postgres
ENV DATABASE_PASSOWORD=postgres
ENV DATABASE_DRIVER=org.postgresql.Driver
ENV POOL=10

CMD java -javaagent:/opt/app/jmx_prometheus_javaagent-0.15.0.jar=9091:/opt/app/config.yaml \
    -Xmx200M -Xms200M -XX:MaxMetaspaceSize=256M \
    -jar /opt/app/app.jar
