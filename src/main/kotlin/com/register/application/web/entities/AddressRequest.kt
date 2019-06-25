package com.register.application.web.entities

import com.register.domain.entities.Address

class AddressRequest(
    val street:String,
    val city: String,
    val number: Int
) {
    fun toModel(): Address = Address(
        street = this.street,
        city = this.city,
        number = this.number
    )
}
