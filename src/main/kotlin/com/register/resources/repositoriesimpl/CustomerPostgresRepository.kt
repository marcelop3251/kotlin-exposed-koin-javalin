package com.register.resources.repositoriesimpl

import com.register.domain.entities.Customer
import com.register.domain.repository.AddressRepository
import com.register.domain.repository.CustomerRepository
import com.register.resources.extensions.toClient
import com.register.resources.schemas.AddressSchema
import com.register.resources.schemas.ClientSchema
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory

class CustomerPostgresRepository(
    private val addressRepository: AddressRepository
) : CustomerRepository {

    private val logger = LoggerFactory.getLogger(CustomerPostgresRepository::class.java)

    override fun save(entity: Customer): Customer = transaction {

            val address = addressRepository.save(entity.address)

            logger.info("Save entity with success")
            val result = ClientSchema.insert {
                it[age] = entity.age
                it[name] = entity.name
                it[addressId] = address.id!!
            }
            entity.copy(id = result[ClientSchema.id],address = address)
        }

    override fun findById(id: Int) = transaction {
        ClientSchema.join(AddressSchema, JoinType.INNER, ClientSchema.addressId, AddressSchema.id).select {
            ClientSchema.id eq id
        }.first().let {
            it.toClient()
        }
    }
}
