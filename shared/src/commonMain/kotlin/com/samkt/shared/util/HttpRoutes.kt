package com.samkt.shared.util

object HttpRoutes {
    private const val BASE_URL = "https://www.freetogame.com/api/"
    private const val ALL_GAMES = "games"
    private const val GAME = "game"

    fun getAllGames(): String {
        return buildString {
            append(BASE_URL)
            append(ALL_GAMES)
        }
    }

    fun getGameCategories(): String {
        return buildString {
            append(BASE_URL)
            append(ALL_GAMES)
        }
    }

    fun getGame():String{
        return buildString {
            append(BASE_URL)
            append(GAME)
        }
    }

}