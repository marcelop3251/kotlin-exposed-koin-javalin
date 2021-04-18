package com.register.domain.entities

data class Customer(
    val id: Int? = null,
    val name:String,
    val age: Int,
    val address: Address
)