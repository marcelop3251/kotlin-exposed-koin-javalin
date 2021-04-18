package com.register.domain

import com.register.application.builder.ClientRequestBuild
import com.register.domain.entities.Customer
import com.register.domain.service.RegisterService
import com.register.resources.repositoriesimpl.CustomerPostgresRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RegisterServiceTest {

    private val clientRepositoryMock = mockk<CustomerPostgresRepository>()
    private val registerService = RegisterService(clientRepositoryMock)
    private val clientBuild = ClientRequestBuild.build().toModel()


    @Test
    fun should_save_client(){
        every{ clientRepositoryMock.save(clientBuild) } returns generateClient(clientBuild)
        val client = registerService.save(clientBuild)
        assertNotNull(client)
        verify { clientRepositoryMock.save(clientBuild) }
    }

    private fun generateClient(customer: Customer) = customer
        .let {
            it.copy(
                id=1,
                address = it.address.copy(id= 1)
            )
        }

}