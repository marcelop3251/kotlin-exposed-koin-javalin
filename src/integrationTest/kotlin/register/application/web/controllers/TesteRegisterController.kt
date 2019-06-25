package com.register.application.web.controllers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.register.application.RegisterMain
import com.register.application.config.DataSource
import com.register.application.web.entities.ClientResponse
import com.register.extensions.payload
import com.zaxxer.hikari.HikariConfig
import io.javalin.Javalin
import io.mockk.every
import io.mockk.mockkObject
import khttp.responses.Response
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.context.stopKoin

class TestRegisterController{

    private lateinit var app: Javalin
    private val url = "http://localhost:7000/"

    @BeforeEach
    fun setUp() {
        mockDatasource()
        app = RegisterMain.startApplication()
    }

    @AfterEach
    fun tearDown() {
        app.stop()
        stopKoin()
    }

    @Test
    fun testPostRegisterClient(){
        val response = createClient()
        assertEquals(201,response.statusCode)
    }

    @Test
    fun testGetClient(){
        createClient()
        val response = khttp.get(url = url + "registry-client")
        val client = response.text.deserialize<List<ClientResponse>>()
        assertTrue(client.isNotEmpty())
        assertEquals(200,response.statusCode)
    }

    private fun mockDatasource(){
        mockkObject(DataSource)
        every { DataSource.getConfig() } returns HikariConfig().apply {
            this.jdbcUrl = "jdbc:h2:mem:testdb"
            this.maximumPoolSize = 10
            this.username = "sa"
            this.password = "sa"
        }
    }

    private fun createClient(): Response {
        return khttp.post(
            url = url + "registry-client",
            data = "registry-client.json".payload()
        )
    }
}

inline fun <reified T : Any> String.deserialize(): T = jacksonObjectMapper().readValue(this)