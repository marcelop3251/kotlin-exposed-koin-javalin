package com.register.application.config

import com.natpryce.konfig.*

class EnvironmentConfig(
    configuration: Configuration = EnvironmentVariables()
) {

    val urlJdbc = configuration[DATABASE_JDBC_URL]
    val username = configuration[DATABASE_USER_NAME]
    val password = configuration[DATABASE_PASSOWORD]
    val driver = configuration[DATABASE_DRIVER]
    val pool = configuration[POOL]

    companion object {
        private val DATABASE_JDBC_URL by stringType
        private val DATABASE_USER_NAME by stringType
        private val DATABASE_PASSOWORD by stringType
        private val DATABASE_DRIVER by stringType
        private val POOL by intType
    }
}