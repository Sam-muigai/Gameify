package com.samkt.gameify.presentation.category_screen

import com.samkt.gameify.domain.model.Games

data class CategoryStates(
    val isLoading:Boolean = false,
    val games:List<Games> = emptyList(),
    val errorMessage:String? = null
)