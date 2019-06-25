package com.register.resources.repositoriesimpl

import com.register.domain.entities.Address
import com.register.domain.entities.Client
import com.register.domain.repository.Repository
import com.register.resources.extensions.toClient
import com.register.resources.schemas.ClientSchema
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory

class ClientRepository(
    private val addressRepository: AddressRepository
) : Repository<Client> {

    private val logger = LoggerFactory.getLogger(ClientRepository::class.java)

    override fun save(entity: Client): Client = transaction {

            val address = addressRepository.save(entity.address)

            logger.info("Save entity with success")
            val result = ClientSchema.insert {
                it[age] = entity.age
                it[name] = entity.name
                it[addressId] = address.id!!
            }
            entity.copy(id=result.get(ClientSchema.id),address = address)
        }

    override fun findAll(): List<Client> = transaction {
         ClientSchema.selectAll().map {
            it.toClient()
        }.toList()
    }

}
