package com.samkt.shared.data.remote

import com.samkt.shared.data.remote.dto.GameDto
import com.samkt.shared.data.remote.dto.GamesResponseDto

interface FreeToGameApi {

    suspend fun getAllGames(): List<GamesResponseDto>

    suspend fun getGameById(
        id: Int,
    ): GameDto

    suspend fun getGamesByCategory(
        category: String,
    ): List<GamesResponseDto>
}


