package com.register.application.builder

import com.register.application.web.entities.CustomerRequest

class ClientRequestBuild {

    val name = "Marcelo dfdf"
    val age  = 30
    val address = AddressRequestBuild.build()

    companion object {
        fun build(client: ClientRequestBuild = ClientRequestBuild()): CustomerRequest = CustomerRequest(
            name = client.name,
            age = client.age,
            address =  client.address
        )
    }
}
