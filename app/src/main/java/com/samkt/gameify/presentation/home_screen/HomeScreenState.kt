package com.samkt.gameify.presentation.home_screen

import com.samkt.gameify.domain.model.Games

data class HomeScreenState(
    val isShooterGamesLoading:Boolean = false,
    val isRacingGamesLoading:Boolean = false,
    val isSportsGamesLoading:Boolean = false,
    val isAnimeGamesLoading:Boolean = false,
    val shooterGames:List<Games> = emptyList(),
    val animeGames:List<Games> = emptyList(),
    val racingGames:List<Games> = emptyList(),
    val sportsGames:List<Games> = emptyList(),
    val errorMessage:String? = null
)
