package com.samkt.gameify.data.remote

import com.samkt.gameify.data.remote.dto.GameDto
import com.samkt.gameify.data.remote.dto.GamesResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface FreeToGameApi {

    @GET("games")
    suspend fun getAllGames(): List<GamesResponseDto>

    @GET("game")
    suspend fun getGameById(
        @Query("id") id: Int,
    ): GameDto

    @GET("games")
    suspend fun getGamesByCategory(
        @Query("category") category: String,
    ): List<GamesResponseDto>
}
