package com.samkt.shared.di


import com.samkt.shared.data.remote.FreeToGameApi
import com.samkt.shared.data.remote.FreeToGameApiImpl
import com.samkt.shared.data.repository.GamesRepositoryImpl
import com.samkt.shared.domain.repository.GamesRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

interface AppContainer {


    fun freeToGameApi(): FreeToGameApi

    fun gameRepository(): GamesRepository
}

class AppContainerImpl : AppContainer {

    private fun client(): HttpClient {
        return HttpClient {
            install(Logging) {
                level = LogLevel.ALL
                logger =
                    object : Logger {
                        override fun log(message: String) {

                        }
                    }
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        useAlternativeNames = true
                        ignoreUnknownKeys = true
                        encodeDefaults = false
                    },
                )
            }
        }
    }

    override fun freeToGameApi(): FreeToGameApi {
        return FreeToGameApiImpl(client())
    }


    override fun gameRepository(): GamesRepository {
        return GamesRepositoryImpl(freeToGameApi())
    }
}