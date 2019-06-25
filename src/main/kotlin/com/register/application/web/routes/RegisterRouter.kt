package com.register.application.web.routes

import io.javalin.apibuilder.ApiBuilder

class RegisterRouter {

    fun register(){
        ApiBuilder.path("/registerClient"){
        }
    }
}