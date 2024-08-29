package com.samkt.shared.data.repository

import com.samkt.shared.data.remote.FreeToGameApi
import com.samkt.shared.data.remote.mappers.toGame
import com.samkt.shared.data.remote.mappers.toGames
import com.samkt.shared.domain.model.Game
import com.samkt.shared.domain.model.Games
import com.samkt.shared.domain.repository.GamesRepository
import com.samkt.shared.util.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GamesRepositoryImpl(
    private val api: FreeToGameApi,
) : GamesRepository {

    override suspend fun getGameById(id: Int): Flow<com.samkt.shared.util.Resources<Game>> {
        return flowOf(
            safeApiCall(Dispatchers.IO) {
                api.getGameById(id).toGame()
            },
        )
    }

    override suspend fun getAllGames(): Flow<com.samkt.shared.util.Resources<List<Games>>> {
        return flowOf(
            safeApiCall(Dispatchers.IO) {
                api.getAllGames().map { it.toGames() }
            },
        )
    }

    override suspend fun getGameByCategory(category: String): Flow<com.samkt.shared.util.Resources<List<Games>>> {
        return flowOf(
            safeApiCall(Dispatchers.IO) {
                api.getGamesByCategory(category).map { it.toGames() }
            },
        )
    }
}
