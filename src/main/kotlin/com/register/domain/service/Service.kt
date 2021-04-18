package com.register.domain.service

interface Service<T> {

    fun save(entity: T): T
    fun findById(id: Int): T

}