package com.samkt.gameify.presentation.category_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samkt.gameify.domain.repository.GamesRepository
import com.samkt.gameify.util.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: GamesRepository,
    savedStateHandle: SavedStateHandle
):ViewModel(){

    private var _uiState = MutableStateFlow(CategoryStates())
    val uiState = _uiState.asStateFlow()


    init {
        val category = savedStateHandle.get<String>("category") ?: "anime"
        getGames(category)
    }

    private fun getGames(category: String) {
        viewModelScope.launch {
            repository.getGamesByCategory(category).onEach { result ->
                when(result){
                    is Resources.Success ->{
                        delay(2000)
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                games = result.data ?: emptyList()
                            )
                        }
                    }
                    is Resources.Loading ->{
                        _uiState.update {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }
                    is Resources.Error ->{
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = result.message
                            )
                        }
                    }
                }
            }.launchIn(this)
        }
    }


}