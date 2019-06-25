package com.register.repository

import com.register.application.build.ClientRequestBuild
import com.register.resources.repositoriesimpl.AddressRepository
import com.register.resources.repositoriesimpl.ClientRepository
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class ClientRepositoryTest : RepositoryBase() {

    private val clientBuild = ClientRequestBuild.build().toModel()

    private val clientRepository = ClientRepository(AddressRepository())

    @Test
    fun should_persist_client(){
        val client = clientRepository.save(clientBuild)
        assertNotNull(client)
    }

    @Test
    fun should_return_list_clients(){
        for(i in 0..2) {
            clientRepository.save(clientBuild)
        }
        val findAll = clientRepository.findAll()
        assertThat(3).isEqualTo(findAll.size)
    }

}