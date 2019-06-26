package com.register.application.config

import com.zaxxer.hikari.HikariConfig

object DataSource {

    fun getConfig(): HikariConfig = HikariConfig().apply {
        this.jdbcUrl = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=postgres"
        this.maximumPoolSize = 10
        this.driverClassName = "org.postgresql.Driver"
    }
}