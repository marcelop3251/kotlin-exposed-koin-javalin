# kotlin-exposed-koin-javalin
Microservice with kotlin, exposed, koin and javalin.

This microservice only demonstrates how we can combine the technologies mentioned.

Environment variables that sould be available.
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