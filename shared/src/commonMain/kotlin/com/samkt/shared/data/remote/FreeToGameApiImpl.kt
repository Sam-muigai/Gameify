package com.samkt.shared.data.remote

import com.samkt.shared.data.remote.dto.GameDto
import com.samkt.shared.data.remote.dto.GamesResponseDto
import com.samkt.shared.util.HttpRoutes
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

class FreeToGameApiImpl(
    private val client: HttpClient
): FreeToGameApi {
    override suspend fun getAllGames(): List<GamesResponseDto> {
        return client.get {
            url(urlString = HttpRoutes.getAllGames())
        }.bodyAsText().let {
                Json.decodeFromString(it)
            }
    }

    override suspend fun getGameById(id: Int): GameDto {
        return client.get {
            url(urlString = HttpRoutes.getGame())
            parameter("id",id)
        }.bodyAsText().let {
            Json.decodeFromString(it)
        }
    }

    override suspend fun getGamesByCategory(category: String): List<GamesResponseDto> {
        return client.get {
            url(urlString = HttpRoutes.getGameCategories())
            parameter("category",category)
        }.bodyAsText().let {
            Json.decodeFromString(it)
        }
    }
}