package com.register.application.web.controllers

import com.register.application.web.errors.HandlerError
import com.register.application.web.errors.HttpError
import com.register.application.web.exceptions.InvalidPaiload
import io.javalin.Context
import io.mockk.mockk
import io.mockk.verify
import org.eclipse.jetty.http.HttpStatus
import org.junit.jupiter.api.Test
import java.lang.Exception

class HandlerErrorTest {

    private var ctxMock = mockk<Context>(relaxed = true)

    @Test
    fun should_return_invalid_payload() {
        val exception = InvalidPaiload(
            type = "Invalid Payload",
            message = "Paiload not Processable"
        )
        HandlerError.handlerErrorException(exception, ctxMock)

        verify { ctxMock.status(HttpStatus.UNPROCESSABLE_ENTITY_422) }
        verify { ctxMock.json(HttpError(exception.type,exception.message))}

    }

    @Test
    fun should_return_internal_server_error(){
        val exception = Exception("Unknown error")
        HandlerError.handlerErrorException(exception, ctxMock)

        verify { ctxMock.status(HttpStatus.INTERNAL_SERVER_ERROR_500) }
        verify { ctxMock.json(HttpError("Unknown error","error not identified"))}
    }
}