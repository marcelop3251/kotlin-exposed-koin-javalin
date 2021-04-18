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
## Database

* Postgres 

You can upload a container with docker by running the following command.

`docker run --name register -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres --network host -p 5432:5432 -d postgres`

### Run application with javaagent prometheus

` java -javaagent:./agent/jmx_prometheus_javaagent-0.15.0.jar=9091:./agent/config.yaml -jar build/libs/register-1.0.0.jar
`

### Run prometheus locally

`docker run -p 9090:9090 -v {dir}/prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus
`
