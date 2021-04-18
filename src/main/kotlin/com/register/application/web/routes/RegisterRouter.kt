package com.register.application.web.routes

import com.register.application.web.controllers.RegisterController
import io.javalin.apibuilder.ApiBuilder.*


class RegisterRouter(
    private val controller: RegisterController
) {

    fun register(){
        path("/registry-customer") {
            post { ctx -> ctx.json(controller.registerClient(ctx)) }
            get("/:id") { ctx -> ctx.json(controller.getClientById(ctx))}
        }
    }
}