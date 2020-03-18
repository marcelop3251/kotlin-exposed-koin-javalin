package com.register.application.web.controllers

import com.register.application.web.entities.ClientRequest
import com.register.application.web.entities.ClientResponse
import com.register.application.web.exceptions.InvalidPaiload
import com.register.domain.entities.Client
import com.register.domain.service.Service
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.http.BadRequestResponse
import io.javalin.http.Context
import org.eclipse.jetty.http.HttpStatus
import org.slf4j.LoggerFactory

class RegisterController(
    private val registerService : Service<Client>
) {

    private val logger = LoggerFactory.getLogger(RegisterController::class.java)

    fun router() {
        path("/registry-client") {
            post { ctx -> ctx.json(registerClient(ctx)) }
            get { ctx -> ctx.json(listClients(ctx)) }
        }

    }

    fun registerClient(ctx: Context): ClientResponse = try{
        ctx.bodyAsClass(ClientRequest::class.java).let {
            logger.info("Save client with name ${it.name}")
            ctx.status(HttpStatus.CREATED_201)
            ClientResponse.toResponse(registerService.save(it.toModel()))
        }
    } catch (ex : BadRequestResponse){
        logger.error(ex.toString())
        throw InvalidPaiload(type = "Payload invalid",
            message = ex.message.toString())
    }

    fun listClients(ctx: Context): List<ClientResponse> {
        logger.info("Find all Clients")
        return registerService.findAll().map { ClientResponse.toResponse(it) }.also {
            ctx.status(HttpStatus.OK_200)
        }
    }

}
