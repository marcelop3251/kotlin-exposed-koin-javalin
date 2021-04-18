package com.register.application.web.controllers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.natpryce.konfig.ConfigurationProperties
import com.register.application.RegisterMain
import com.register.application.config.EnvironmentConfig
import com.register.application.config.configModule
import com.register.application.web.entities.CustomerResponse
import io.javalin.Javalin
import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import khttp.responses.Response
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.context.stopKoin
import org.koin.dsl.module

class TestRegisterController {

    private lateinit var app: Javalin
    private val url = "http://localhost:7000/"

    private val configEnvironment = ConfigurationProperties.fromResource("application.properties")

    private val configModuleMock = module {
        single { EnvironmentConfig(configEnvironment) }
    }

    @BeforeEach
    fun setUp() {
        mockkStatic("com.register.application.config.ConfigModuleDatabaseKt")
        every { configModule } returns configModuleMock
        app = RegisterMain.startApplication()
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
        app.stop()
        stopKoin()
    }

    @Test
    fun testPostRegisterClient() {
        val response = createClient()
        assertEquals(201,response.statusCode)
    }

    @Test
    fun testGetClient() {
        val customerByPost = createClient().text.deserialize<CustomerResponse>()
        val response = khttp.get(url = url + "registry-customer/${customerByPost.id}")
        val client = response.text.deserialize<CustomerResponse>()
        assertNotNull(client)
        assertEquals(200, response.statusCode)
    }

    private fun createClient(): Response {
        val resource = javaClass.getResource("/samples/registry-customer.json").readText()
        return khttp.post(
            url = url + "registry-customer",
            data = resource
        )
    }
}

inline fun <reified T : Any> String.deserialize(): T = jacksonObjectMapper().readValue(this)