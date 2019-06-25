package com.register.repository

import com.register.resources.schemas.AddressSchema
import com.register.resources.schemas.ClientSchema
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

open class RepositoryBase {

    @BeforeEach
    fun setup() {
        Database.connect(
            HikariDataSource(HikariConfig().apply {
                this.jdbcUrl = "jdbc:h2:mem:testdb"
                this.maximumPoolSize = 10
                this.username = "sa"
                this.password = "sa"
            })
        )

        transaction {
            SchemaUtils.create(ClientSchema, AddressSchema)
        }
    }

    @AfterEach
    fun tearDown(){
        transaction {
            SchemaUtils.drop(ClientSchema,AddressSchema)
        }
    }
}