package com.register.domain.service

import com.register.domain.entities.Client
import com.register.domain.repository.Repository
import org.slf4j.LoggerFactory
import java.lang.Exception

class RegisterService (
    private val clientRepository: Repository<Client>
) : Service<Client> {

    private val logger = LoggerFactory.getLogger(RegisterService::class.java)

    override fun save(entity: Client): Client = try {
        clientRepository.save(entity).also {
            logger.info("Client save with success")
        }
    } catch (ex: Exception) {
        logger.error(ex.toString())
        throw Exception("Not was possible save entity")
    }

    override fun findAll(): List<Client> {
        return clientRepository.findAll()
    }

}
