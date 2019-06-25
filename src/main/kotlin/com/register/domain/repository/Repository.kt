package com.register.domain.repository

interface Repository<T> {

    fun save(entity : T): T
    fun findAll(): List<T>

}
