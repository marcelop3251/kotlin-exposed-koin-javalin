package com.register.application.web.controllers

import com.register.application.build.ClientRequestBuild
import com.register.application.web.entities.ClientRequest
import com.register.application.web.exceptions.InvalidPaiload
import com.register.domain.entities.Client
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
    private val registerServiceMock = mockk<Service<Client>>()
    private val clientRequest = ClientRequestBuild.build()
    private val registerController = RegisterController(registerServiceMock)

    @Test
     fun should_return_client(){
        every { ctxMock.bodyAsClass(ClientRequest::class.java) } returns clientRequest
        every { registerServiceMock.save(clientRequest.toModel())} returns  generateClient(clientRequest)

        val registerClient = registerController.registerClient(ctxMock)

        Assertions.assertNotNull(registerClient)
        verify{ ctxMock.status(HttpStatus.CREATED_201)}
        verify { ctxMock.bodyAsClass(ClientRequest::class.java) }
        verify { registerServiceMock.save(clientRequest.toModel()) }
    }

    @Test
    fun should_return_list_clients(){
        every {registerServiceMock.findAll()} returns listOf(generateClient(clientRequest))

        val registerClients = registerController.listClients(ctxMock)

        assertThat(1).isEqualTo(registerClients.size)
        verify{ ctxMock.status(HttpStatus.OK_200)}
        verify{ registerServiceMock.findAll() }

    }

    @Test
    fun shoud_return_InvalidPaiload(){
        every { ctxMock.bodyAsClass(ClientRequest::class.java) } throws BadRequestResponse("Couldn't deserialize body")

       Assertions.assertThrows(InvalidPaiload::class.java){
           registerController.registerClient(ctxMock)
       } .let {
           assertThat(it.type).isEqualTo("Payload invalid")
       }
    }

    private fun generateClient(clientRequest: ClientRequest) = clientRequest.toModel()
        .let {
            it.copy(
                id=1,
                address = it.address.copy(id= 1)
            )
    }



}

