package com.samkt.gameify.presentation.search_screen

import com.samkt.gameify.domain.model.Games

data class SearchScreenStates(
    val searchTerm:String = "",
    val games:List<Games> = emptyList()
)
