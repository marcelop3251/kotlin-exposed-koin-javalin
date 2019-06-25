package com.register.resources.schemas


import org.jetbrains.exposed.sql.Table

object ClientSchema : Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val name = varchar("name",26)
    val age = integer("age")
    val addressId = integer("addressId")
}