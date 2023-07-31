package com.samkt.gameify.presentation.search_screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor():ViewModel(){

    private var _uiState = MutableStateFlow(SearchScreenStates())
    val uiState = _uiState.asStateFlow()


    fun onEvent(event: SearchScreenEvents){
        when(event){
            is SearchScreenEvents.OnValueChange ->{
                _uiState.update {
                    it.copy(
                        searchTerm = event.value
                    )
                }
            }
        }
    }

}