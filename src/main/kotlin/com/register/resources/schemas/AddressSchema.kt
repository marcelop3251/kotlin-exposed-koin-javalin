package com.register.resources.schemas


import org.jetbrains.exposed.sql.Table


object AddressSchema : Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val street = varchar("street",100)
    val city = varchar("city",50)
    val number = integer("number")
}