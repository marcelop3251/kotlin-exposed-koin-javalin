package com.register.application

import com.register.application.config.configModule
import com.register.application.config.modulesAll
import com.register.application.web.RegisterInit
import io.javalin.Javalin
import org.koin.core.context.startKoin

object RegisterMain {

    @JvmStatic
    fun main(args: Array<String>) {

       startApplication()

    }

    fun startApplication(): Javalin{
        startKoin {
            modules(modulesAll, configModule)
        }

       return RegisterInit.start()
    }
}

