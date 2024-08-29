package com.samkt.shared.presentation.gameScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samkt.shared.domain.model.Game
import com.samkt.shared.domain.repository.GamesRepository
import com.samkt.shared.util.Resources
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class GameViewModel(
    private val repository: GamesRepository,
) : ViewModel() {

    private var _gameScreenState = MutableStateFlow(GameScreenState())
    val gameScreenState = _gameScreenState.asStateFlow()

    fun getGame(id: Int) {
        viewModelScope.launch {
            _gameScreenState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null
                )
            }
            repository.getGameById(id).onEach { result ->
                when (result) {
                    is Resources.Success -> {
                        _gameScreenState.update {
                            it.copy(
                                isLoading = false,
                                data = result.data,
                            )
                        }
                    }

                    is Resources.Error -> {
                        _gameScreenState.update {
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

data class GameScreenState(
    val isLoading: Boolean = false,
    val data: Game? = null,
    val errorMessage: String? = null,
)
