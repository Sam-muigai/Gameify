package com.samkt.gameify.presentation.gameScreen

import com.samkt.gameify.domain.model.Game

data class GameScreenState(
    val isLoading: Boolean = false,
    val data: Game? = null,
    val errorMessage: String? = null,
)
