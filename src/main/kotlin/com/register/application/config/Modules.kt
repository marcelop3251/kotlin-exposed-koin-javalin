package com.register.application.config


import com.register.application.web.controllers.RegisterController
import com.register.application.web.routes.RegisterRouter
import com.register.domain.entities.Customer
import com.register.domain.repository.AddressRepository
import com.register.domain.repository.CustomerRepository
import com.register.domain.service.RegisterService
import com.register.domain.service.Service
import com.register.resources.repositoriesimpl.AddressPostgresRepository
import com.register.resources.repositoriesimpl.CustomerPostgresRepository
import org.koin.dsl.module


val modulesAll = module {

    single<AddressRepository> { AddressPostgresRepository() }

    single<CustomerRepository> { CustomerPostgresRepository(get())}

    single<Service<Customer>> { RegisterService(get()) }

    single { RegisterController(get()) }

    single { RegisterRouter(get()) }

}