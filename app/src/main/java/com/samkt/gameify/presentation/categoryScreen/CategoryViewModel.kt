package com.samkt.gameify.presentation.categoryScreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samkt.gameify.domain.model.Games
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
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private var _categoryScreenUiState = MutableStateFlow(CategoryStates())
    val categoryScreenUiState = _categoryScreenUiState.asStateFlow()

    init {
        val category = savedStateHandle.get<String>("category") ?: ""
        getGames(category)
    }

    private fun getGames(category: String) {
        viewModelScope.launch {
            repository.getAllGames().onEach { result ->
                when (result) {
                    is Resources.Success -> {
                        val filterGames = result.data?.filter { it.genre.lowercase() == category.lowercase() } ?: emptyList()
                        _categoryScreenUiState.update {
                            it.copy(
                                isLoading = false,
                                games = filterGames,
                            )
                        }
                    }

                    is Resources.Loading -> {
                        _categoryScreenUiState.update {
                            it.copy(
                                isLoading = true,
                            )
                        }
                    }

                    is Resources.Error -> {
                        _categoryScreenUiState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = result.message,
                            )
                        }
                    }
                }
            }.launchIn(this)
        }
    }
}

data class CategoryStates(
    val isLoading: Boolean = false,
    val games: List<Games> = emptyList(),
    val errorMessage: String? = null,
)