package com.samkt.gameify.data.repository

import com.samkt.gameify.data.remote.FreeToGameApi
import com.samkt.gameify.data.remote.mappers.toGame
import com.samkt.gameify.data.remote.mappers.toGames
import com.samkt.gameify.domain.model.Game
import com.samkt.gameify.domain.model.Games
import com.samkt.gameify.domain.repository.GamesRepository
import com.samkt.gameify.util.Resources
import com.samkt.gameify.util.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GamesRepositoryImpl @Inject constructor(
    private val api: FreeToGameApi,
) : GamesRepository {

    override suspend fun getGameById(id: Int): Flow<Resources<Game>> {
        return flowOf(
            safeApiCall(Dispatchers.IO) {
                api.getGameById(id).toGame()
            },
        )
    }

    override suspend fun getAllGames(): Flow<Resources<List<Games>>> {
        return flowOf(
            safeApiCall(Dispatchers.IO) {
                api.getAllGames().map { it.toGames() }
            },
        )
    }

    override suspend fun getGameByCategory(category: String): Flow<Resources<List<Games>>> {
        return flowOf(
            safeApiCall(Dispatchers.IO) {
                api.getGamesByCategory(category).map { it.toGames() }
            },
        )
    }
}
