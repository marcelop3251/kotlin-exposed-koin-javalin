#!/bin/bash

export DATABASE_JDBC_URL=jdbc:postgresql://localhost:5432/postgres
export DATABASE_USER_NAME=postgres
export DATABASE_PASSOWORD=postgres
export DATABASE_DRIVER=org.postgresql.Driver
export POOL=10

./gradlew clean build

docker run --name register -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres --network host -d postgres

sleep 2
java -jar build/libs/register-1.0.0.jar