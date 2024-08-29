package com.samkt.shared.domain.repository

import com.samkt.shared.domain.model.Game
import com.samkt.shared.domain.model.Games
import com.samkt.shared.util.Resources
import kotlinx.coroutines.flow.Flow

interface GamesRepository {

    suspend fun getGameById(id: Int): Flow<com.samkt.shared.util.Resources<Game>>

    suspend fun getAllGames(): Flow<com.samkt.shared.util.Resources<List<Games>>>
    suspend fun getGameByCategory(category: String): Flow<com.samkt.shared.util.Resources<List<Games>>>
}
