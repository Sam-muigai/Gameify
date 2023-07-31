package com.samkt.gameify.presentation.search_screen

sealed class SearchScreenEvents {
    data class OnValueChange(val value:String):SearchScreenEvents()
}