package com.samkt.gameify.domain.repository

import com.samkt.gameify.domain.model.Game
import com.samkt.gameify.domain.model.Games
import com.samkt.gameify.util.Resources
import kotlinx.coroutines.flow.Flow

interface GamesRepository {

    suspend fun getGameById(id: Int): Flow<Resources<Game>>

    suspend fun getAllGames(): Flow<Resources<List<Games>>>
    suspend fun getGameByCategory(category: String): Flow<Resources<List<Games>>>
}
