package com.register.extensions

fun String.payload() : String = javaClass.getResource("/$this").readText()