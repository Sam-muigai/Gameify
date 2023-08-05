package com.samkt.gameify.presentation.home_screen

import com.samkt.gameify.domain.model.Games

data class HomeScreenState(
    val isLoading:Boolean = false,
    val shooterGames:List<Games> = emptyList(),
    val fightingGames:List<Games> = emptyList(),
    val racingGames:List<Games> = emptyList(),
    val sportsGames:List<Games> = emptyList(),
    val errorMessage:String? = null
)
