package com.register.repository

import com.register.application.build.AddressRequestBuild
import com.register.resources.repositoriesimpl.AddressRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AddressRepositoyTest : RepositoryBase() {

    private val addressBuild = AddressRequestBuild.build().toModel()

    private val addressRepository = AddressRepository()

    @Test
    fun should_persist_address(){
        val address = addressRepository.save(addressBuild)
        assertNotNull(address)
    }
}