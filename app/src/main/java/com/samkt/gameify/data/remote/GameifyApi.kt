package com.samkt.gameify.data.remote

import com.samkt.gameify.data.remote.dto.GameDto
import com.samkt.gameify.data.remote.dto.GamesDtoItem
import retrofit2.http.GET
import retrofit2.http.Query

interface GameifyApi {

    @GET("games")
    suspend fun getAllGames():List<GamesDtoItem>

    @GET("game")
    suspend fun getGameById(
        @Query("id") id:Int
    ):GameDto

    companion object{
        const val BASE_URL = "https://www.freetogame.com/api/"
    }
}