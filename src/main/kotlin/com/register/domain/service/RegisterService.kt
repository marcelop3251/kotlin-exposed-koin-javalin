package com.register.domain.service

import com.register.domain.entities.Customer
import com.register.domain.repository.CustomerRepository
import org.slf4j.LoggerFactory
import java.lang.Exception

class RegisterService (
    private val customerRepository: CustomerRepository
) : Service<Customer> {

    private val logger = LoggerFactory.getLogger(RegisterService::class.java)

    override fun save(entity: Customer): Customer = try {
        customerRepository.save(entity).also {
            logger.info("Client save with success")
        }
    } catch (ex: Exception) {
        logger.error(ex.toString())
        throw Exception("Not was possible save entity")
    }

    override fun findById(id: Int): Customer {
        return customerRepository.findById(id)
    }

}
