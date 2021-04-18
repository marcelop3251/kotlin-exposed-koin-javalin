package com.register.resources.repositoriesimpl

import com.register.domain.entities.Address
import com.register.domain.repository.AddressRepository
import com.register.resources.schemas.AddressSchema
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory

class AddressPostgresRepository : AddressRepository {

    private val logger = LoggerFactory.getLogger(AddressPostgresRepository::class.java)


    override fun save(entity: Address): Address = transaction{
        logger.info("Save Address with success")
        val result = AddressSchema.insert {
            it[city] = entity.city
            it[number] = entity.number
            it[street] = entity.street
        }
        entity.copy(id = result.get(AddressSchema.id))
    }
}