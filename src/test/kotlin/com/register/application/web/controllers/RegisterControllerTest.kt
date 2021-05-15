package com.register.application.web.controllers

import com.register.application.builder.ClientRequestBuild
import com.register.application.web.entities.CustomerRequest
import com.register.application.web.exceptions.InvalidPaiload
import com.register.domain.entities.Customer
import com.register.domain.service.Service
import io.javalin.http.BadRequestResponse
import io.javalin.http.Context
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.*
import org.eclipse.jetty.http.HttpStatus
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class RegisterControllerTest {

    private var ctxMock =  mockk<Context>(relaxed = true)
    private val registerServiceMock = mockk<Service<Customer>>()
    private val clientRequest = ClientRequestBuild.build()
    private val registerController = RegistryController(registerServiceMock)

    @Test
     fun should_return_client(){
        every { ctxMock.bodyAsClass(CustomerRequest::class.java) } returns clientRequest
        every { registerServiceMock.save(clientRequest.toModel())} returns  generateClient(clientRequest)

        val registerClient = registerController.registerClient(ctxMock)

        Assertions.assertNotNull(registerClient)
        verify{ ctxMock.status(HttpStatus.CREATED_201)}
        verify { ctxMock.bodyAsClass(CustomerRequest::class.java) }
        verify { registerServiceMock.save(clientRequest.toModel()) }
    }


    @Test
    fun shoud_return_InvalidPaiload(){
        every { ctxMock.bodyAsClass(CustomerRequest::class.java) } throws BadRequestResponse("Couldn't deserialize body")

       Assertions.assertThrows(InvalidPaiload::class.java){
           registerController.registerClient(ctxMock)
       } .let {
           assertThat(it.type).isEqualTo("Payload invalid")
       }
    }

    private fun generateClient(customerRequest: CustomerRequest) = customerRequest.toModel()
        .let {
            it.copy(
                id=1,
                address = it.address.copy(id= 1)
            )
    }



}

