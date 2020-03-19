package com.register.domain

import com.register.application.builder.ClientRequestBuild
import com.register.domain.entities.Client
import com.register.domain.service.RegisterService
import com.register.resources.repositoriesimpl.ClientRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RegisterServiceTest {

    private val clientRepositoryMock = mockk<ClientRepository>()
    private val registerService = RegisterService(clientRepositoryMock)
    private val clientBuild = ClientRequestBuild.build().toModel()


    @Test
    fun should_save_client(){
        every{ clientRepositoryMock.save(clientBuild) } returns generateClient(clientBuild)
        val client = registerService.save(clientBuild)
        assertNotNull(client)
        verify { clientRepositoryMock.save(clientBuild) }
    }

    @Test
    fun should_return_list_clients(){
        every { clientRepositoryMock.findAll() } returns listOf(clientBuild)
        val clients = registerService.findAll()

        assertThat(clients.size).isGreaterThan(0)
        verify { clientRepositoryMock.findAll() }
    }

    private fun generateClient(client: Client) = client
        .let {
            it.copy(
                id=1,
                address = it.address.copy(id= 1)
            )
        }

}