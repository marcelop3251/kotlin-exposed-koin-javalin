package com.register.domain.repository

import com.register.domain.entities.Customer

interface CustomerRepository {

    fun save(entity : Customer): Customer
    fun findById(id: Int): Customer
}