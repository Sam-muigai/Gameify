package com.samkt.gameify.presentation.homeScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samkt.gameify.domain.model.Games
import com.samkt.gameify.domain.repository.GamesRepository
import com.samkt.gameify.util.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

const val RESULT = "result"

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: GamesRepository,
) : ViewModel() {

    private var _homeScreenState = MutableStateFlow(HomeScreenState())
    val homeScreenState = _homeScreenState.asStateFlow()

    init {
        getAllGames()
    }

    private fun getAllGames() {
        viewModelScope.launch {
            _homeScreenState.update {
                it.copy(
                    isLoading = true,
                )
            }
            repository.getAllGames().onEach { result ->
                when (result) {
                    is Resources.Success -> {
                        val games = result.data
                        val shootingGames =
                            games?.filter { it.genre.lowercase() == "shooter" } ?: emptyList()
                        val racingGames =
                            games?.filter { it.genre.lowercase() == "racing" } ?: emptyList()
                        val sportsGames =
                            games?.filter { it.genre.lowercase() == "sports" } ?: emptyList()
                        val fightingGames =
                            games?.filter { it.genre.lowercase() == "fighting" } ?: emptyList()
                        _homeScreenState.update {
                            it.copy(
                                isLoading = false,
                                shooterGames = shootingGames,
                                racingGames = racingGames,
                                sportsGames = sportsGames,
                                fightingGames = fightingGames,
                            )
                        }
                        Log.d(RESULT, (result.data ?: emptyList()).toString())
                    }

                    is Resources.Error -> {
                        val errorMessage = result.message ?: "Error occurred"
                        _homeScreenState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = errorMessage,
                            )
                        }
                        Log.d(RESULT, errorMessage)
                    }
                }
            }.launchIn(this)
        }
    }
}

data class HomeScreenState(
    val isLoading: Boolean = false,
    val shooterGames: List<Games> = emptyList(),
    val fightingGames: List<Games> = emptyList(),
    val racingGames: List<Games> = emptyList(),
    val sportsGames: List<Games> = emptyList(),
    val errorMessage: String? = null,
)
