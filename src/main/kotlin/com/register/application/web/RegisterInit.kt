package com.register.application.web

import com.register.application.config.DataSource
import com.register.application.config.EnvironmentConfig
import com.register.application.web.errors.HandlerError
import com.register.application.web.routes.RegistryRouter
import com.register.resources.schemas.AddressSchema
import com.register.resources.schemas.ClientSchema
import com.zaxxer.hikari.HikariDataSource
import io.javalin.Javalin
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.lang.Exception

object RegisterInit : KoinComponent {

    private val router: RegistryRouter by inject()
    private val environment: EnvironmentConfig by inject()

    fun start(): Javalin {
        Database.connect(HikariDataSource(DataSource.getConfig(environment)))
        transaction {
            SchemaUtils.create(ClientSchema, AddressSchema)
        }

        val app = Javalin.create().start(7000)

        app.before {
           // println("Body -> ${ctx.body()}")
        }

        app.routes {
            router.register()
        }

        app.exception(Exception::class.java, HandlerError::handlerErrorException)
        return app
    }
}