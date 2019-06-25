package com.register.resources.extensions

import com.register.domain.entities.Address
import com.register.domain.entities.Client
import com.register.resources.schemas.AddressSchema
import com.register.resources.schemas.ClientSchema
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select

fun ResultRow.toClient(): Client = Client(
    id = get(ClientSchema.id),
    name =  get(ClientSchema.name),
    age = get(ClientSchema.age),
    address = ClientSchema.join(
        AddressSchema,
        JoinType.INNER,
        ClientSchema.addressId,
        AddressSchema.id
    ).select {ClientSchema.addressId eq get(ClientSchema.addressId)}.first().toAddress()
)


fun ResultRow.toAddress(): Address = Address(
    id = get(AddressSchema.id),
    street = get(AddressSchema.street),
    city = get(AddressSchema.city),
    number = get(AddressSchema.number)

)