package com.register.application.web.entities

import com.register.domain.entities.Customer

class CustomerRequest(
    val name:String,
    val age: Int,
    val address: AddressRequest
) {

    fun toModel(): Customer = Customer(
        name = this.name,
        age = this.age,
        address = this.address.toModel()
    )
}
