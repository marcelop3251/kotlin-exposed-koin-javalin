package com.register.application.config

import org.koin.dsl.module

val configModule = module {
    single { EnvironmentConfig() }
}