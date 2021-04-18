package com.register.domain.repository

import com.register.domain.entities.Address

interface AddressRepository {
    fun save(address: Address): Address
}