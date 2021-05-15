package com.register.application.web.routes

import com.register.application.web.controllers.RegistryController
import io.javalin.apibuilder.ApiBuilder.*


class RegistryRouter(
    private val controller: RegistryController
) {

    fun register(){
        path("/registry-customer") {
            post { ctx -> ctx.json(controller.registerClient(ctx)) }
            get("/:id") { ctx -> ctx.json(controller.getClientById(ctx))}
        }
    }
}