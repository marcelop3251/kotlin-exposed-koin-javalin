package com.register.application.builder

import com.register.application.web.entities.AddressRequest

class AddressRequestBuild {

    val street = "Julio Marcari"
    val city = "Ribeirão Preto"
    val number = 1500

    companion object {
        fun build(addres: AddressRequestBuild = AddressRequestBuild()): AddressRequest = AddressRequest(
            street = addres.street,
            city = addres.city,
            number =  addres.number
        )
    }
}