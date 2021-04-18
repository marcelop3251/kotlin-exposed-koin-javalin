package com.register.application.web.entities

import com.register.domain.entities.Address

class AddressResponse (
    val id: Int,
    val street:String,
    val city: String,
    val number: Int
)
{
    constructor(address: Address): this(
        id = address.id!!,
        street = address.street,
        city = address.city,
        number = address.number
    )
}
