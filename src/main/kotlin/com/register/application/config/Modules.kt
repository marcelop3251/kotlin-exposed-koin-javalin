package com.register.application.config


import com.register.application.web.controllers.RegisterController
import com.register.domain.entities.Address
import com.register.domain.entities.Client
import com.register.domain.service.RegisterService
import com.register.domain.service.Service
import com.register.resources.repositoriesimpl.ClientRepository
import com.register.domain.repository.Repository
import com.register.resources.repositoriesimpl.AddressRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module


val modulesAll = module {

    single<Repository<Address>>(named("address")) { AddressRepository() }

    single<Repository<Client>>(named("client")) { ClientRepository(get(named("address")))}

    single<Service<Client>> { RegisterService(get(named("client"))) }

    single { RegisterController(get()) }

}