package com.register.application.config

import com.zaxxer.hikari.HikariConfig

object DataSource {

    fun getConfig(environment: EnvironmentConfig): HikariConfig = HikariConfig().apply {
        this.jdbcUrl = environment.urlJdbc
        this.username = environment.username
        this.maximumPoolSize = environment.pool
        this.driverClassName = environment.driver
        this.password = environment.password
    }
}
