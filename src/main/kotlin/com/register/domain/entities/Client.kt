package com.register.domain.entities

data class Client(
    val id: Int? = null,
    val name:String,
    val age: Int,
    val address: Address
)