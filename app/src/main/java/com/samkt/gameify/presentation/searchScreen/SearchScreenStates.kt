package com.samkt.gameify.presentation.searchScreen

import com.samkt.gameify.domain.model.Games

data class SearchScreenStates(
    val searchTerm: String = "",
    val games: List<Games> = emptyList(),
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
)
