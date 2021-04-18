package com.register.application.web.controllers

import com.register.application.web.entities.CustomerRequest
import com.register.application.web.entities.CustomerResponse
import com.register.application.web.exceptions.InvalidPaiload
import com.register.domain.entities.Customer
import com.register.domain.service.Service
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.http.BadRequestResponse
import io.javalin.http.Context
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.ThreadContext
import org.eclipse.jetty.http.HttpStatus

class RegisterController(
    private val registerService : Service<Customer>
) {

    private val logger = LogManager.getLogger(RegisterController::class.java)

   fun getClientById(ctx: Context): CustomerResponse {
        val customerId = ctx.pathParam("id")
        logger.info("Find client from id: $customerId")
        return CustomerResponse(registerService.findById(customerId.toInt()))
    }

    fun registerClient(ctx: Context): CustomerResponse = try {
        ThreadContext.put("correlation-id", "123456789")
        ctx.bodyAsClass(CustomerRequest::class.java).let {
            logger.info("Save client with name ${it.name}")
            ctx.status(HttpStatus.CREATED_201)
            CustomerResponse(registerService.save(it.toModel()))
        }
    } catch (ex : BadRequestResponse){
        logger.error(ex.toString())
        throw InvalidPaiload(type = "Payload invalid",
            message = ex.message.toString())
    }
}
