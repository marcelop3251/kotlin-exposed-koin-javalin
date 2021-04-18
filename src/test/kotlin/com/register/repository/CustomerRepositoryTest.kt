package com.register.repository

import com.register.application.builder.ClientRequestBuild
import com.register.resources.repositoriesimpl.AddressPostgresRepository
import com.register.resources.repositoriesimpl.CustomerPostgresRepository
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class CustomerRepositoryTest : RepositoryBase() {

    private val clientBuild = ClientRequestBuild.build().toModel()

    private val clientRepository = CustomerPostgresRepository(AddressPostgresRepository())

    @Test
    fun should_persist_client(){
        val client = clientRepository.save(clientBuild)
        assertNotNull(client)
    }

}