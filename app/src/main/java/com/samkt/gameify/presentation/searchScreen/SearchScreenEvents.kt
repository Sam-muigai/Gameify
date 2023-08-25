package com.samkt.gameify.presentation.searchScreen

sealed class SearchScreenEvents {
    data class OnValueChange(val value: String) : SearchScreenEvents()
}
