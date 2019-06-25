package com.register.domain.service

interface Service<T> {

    fun save(entity: T): T
    fun findAll(): List<T>

}