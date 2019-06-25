package com.register.application.web.entities

import com.register.domain.entities.Client

class ClientResponse(
    val id: Int,
    val name: String,
    val age: Int,
    val address: AddressResponse
) {

    companion object {
        fun toResponse(client: Client): ClientResponse = ClientResponse(
            id = client.id!!,
            name = client.name,
            age = client.age,
            address = AddressResponse.toResponse(client.address)
        )
    }

}
