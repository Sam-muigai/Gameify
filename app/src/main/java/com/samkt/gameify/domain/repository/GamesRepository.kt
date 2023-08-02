package com.samkt.gameify.domain.repository

import com.samkt.gameify.data.remote.dto.GamesDtoItem
import com.samkt.gameify.domain.model.Game
import com.samkt.gameify.domain.model.Games
import com.samkt.gameify.util.Resources
import kotlinx.coroutines.flow.Flow

interface GamesRepository {

     fun getGamesByCategory(category:String):Flow<Resources<List<Games>>>

    fun getGameById(id:Int):Flow<Resources<Game>>

    fun getAllGames():Flow<Resources<List<Games>>>

}