# kotlin-exposed-koin-javalin
Microservice with kotlin, exposed, koin and javalin.

This microservice only demonstrates how we can combine the technologies mentioned.

Environment variables that should be available.
```
DATABASE_JDBC_URL=<url of jdbc>
DATABASE_USER_NAME=<user name>
DATABASE_PASSOWORD=<password>
DATABASE_DRIVER=<Class driver jdbc>
POOL=<poll of connections>
```
Example values
```
DATABASE_JDBC_URL=jdbc:postgresql://localhost:5432/postgres
DATABASE_USER_NAME=postgres
DATABASE_PASSOWORD=postgres
DATABASE_DRIVER=org.postgresql.Driver
POOL=10
``` 

## Database

* Postgres 

You can upload a container with docker by running the following command.

`docker run --name register -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -p 5432:5432 -d postgres`

### Run application with javaagent prometheus

 If you need to collect metrics with prometheus, just do it.
` java -javaagent:./agent/jmx_prometheus_javaagent-0.15.0.jar=9091:./agent/config.yaml -jar build/libs/register-1.0.0.jar
`

### Run prometheus locally

You will find more detaisl about prometheus.yml here.

https://prometheus.io/docs/prometheus/latest/configuration/configuration/

`docker run -p 9090:9090 -v {dir}/prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus

## Run project locally with sh

If you prefer it is also possible to run this project with sh, but remember that it is necessary that you have java and docker installed.

Start application.

`sh up-application.sh`

Stop application.

`sh down-application.sh`





