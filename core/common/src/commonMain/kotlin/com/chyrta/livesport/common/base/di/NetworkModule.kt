package com.chyrta.livesport.common.base.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single {
        Json {
            encodeDefaults = true
            ignoreUnknownKeys = true
        }
    }
    single {
        HttpClient {
            expectSuccess = true
            defaultRequest {
                url("https://s.livesport.services/api/v2/")
            }
            install(ContentNegotiation) {
                json(get())
            }
        }
    }
}
