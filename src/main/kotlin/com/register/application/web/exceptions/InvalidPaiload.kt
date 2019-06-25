package com.register.application.web.exceptions

class InvalidPaiload (
    val type: String,
    override val message: String
) : Exception(message)
