package com.register.application.web.entities

import com.register.domain.entities.Client

class ClientRequest(
    val name:String,
    val age: Int,
    val address: AddressRequest
) {

    fun toModel(): Client = Client(
        name = this.name,
        age = this.age,
        address = this.address.toModel()
    )
}
