package com.register.application.builder

import com.register.application.web.entities.ClientRequest

class ClientRequestBuild {

    val name = "Marcelo dfdf"
    val age  = 30
    val address = AddressRequestBuild.build()

    companion object {
        fun build(client: ClientRequestBuild = ClientRequestBuild()): ClientRequest = ClientRequest(
            name = client.name,
            age = client.age,
            address =  client.address
        )
    }
}
