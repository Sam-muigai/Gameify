package com.samkt.gameify.data.repository

import com.samkt.gameify.data.remote.GameifyApi
import com.samkt.gameify.data.remote.dto.GamesDtoItem
import com.samkt.gameify.data.remote.mappers.toGame
import com.samkt.gameify.data.remote.mappers.toGames
import com.samkt.gameify.domain.model.Game
import com.samkt.gameify.domain.model.Games
import com.samkt.gameify.domain.repository.GamesRepository
import com.samkt.gameify.util.Resources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GamesRepositoryImpl @Inject constructor(
    private val api: GameifyApi
):GamesRepository{
    override fun getGamesByCategory(category: String): Flow<Resources<List<Games>>> = flow {
       emit(Resources.Loading)
        try {
            val games = api.getGamesByCategory(category).map { it.toGames() }
            emit(Resources.Success(games))
        }catch (e:IOException){
            e.printStackTrace()
            emit(Resources.Error(message = "No internet connection!!"))
        }catch (e:HttpException){
            e.printStackTrace()
            emit(Resources.Error(message = "Server error occurred!!"))
        }catch (e:Exception){
            e.printStackTrace()
            emit(Resources.Error(message = e.message))
        }
    }

    override fun getGameById(id: Int): Flow<Resources<Game>> {
        return flow {
            emit(Resources.Loading)
            try {
                val game = api.getGameById(id).toGame()
                emit(Resources.Success(game))
            }catch (e:IOException){
                e.printStackTrace()
                emit(Resources.Error(message = "No internet connection"))
            }catch (e:HttpException){
                e.printStackTrace()
                emit(Resources.Error(message = "Server error occurred!!"))
            }catch (e:Exception){
                e.printStackTrace()
                emit(Resources.Error(message = e.message))
            }
        }
    }

    override fun getAllGames(): Flow<Resources<List<Games>>> {
        return flow {
            emit(Resources.Loading)
            try {
                val games = api.getAllGames().map { it.toGames() }
                emit(Resources.Success(games))
            }catch (e:IOException){
                e.printStackTrace()
                emit(Resources.Error(message = "No internet connection"))
            }catch (e:Exception){
                e.printStackTrace()
                emit(Resources.Error(message = e.message))
            }
        }
    }
}