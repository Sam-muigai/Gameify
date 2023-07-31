package com.samkt.gameify.presentation.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samkt.gameify.domain.repository.GamesRepository
import com.samkt.gameify.util.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.Error
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: GamesRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(HomeScreenState())
    val uiState = _uiState.asStateFlow()

    init {
        getShooterGames()
        getSportsGames()
        getRacingGames()
        getAnimeGames()
    }

    private fun getSportsGames(category: String = "sports") {
        viewModelScope.launch {
            repository.getGamesByCategory(category).onEach { result ->
                when (result) {
                    is Resources.Loading -> {
                        _uiState.update {
                            it.copy(isSportsGamesLoading = true)
                        }
                    }
                    is Resources.Error -> {
                        _uiState.update {
                            it.copy(
                                isSportsGamesLoading = false,
                                errorMessage = result.message
                            )
                        }
                    }
                    is Resources.Success -> {
                        val games = result.data ?: emptyList()
                        _uiState.update {
                            it.copy(
                                isSportsGamesLoading = false,
                                sportsGames = games
                            )
                        }
                    }
                }
            }.launchIn(this)
        }
    }

    private fun getRacingGames(category: String = "racing") {
        viewModelScope.launch {
            repository.getGamesByCategory(category).onEach { result ->
                when (result) {
                    is Resources.Loading -> {
                        _uiState.update {
                            it.copy(isRacingGamesLoading = true)
                        }
                    }
                    is Resources.Error -> {
                        _uiState.update {
                            it.copy(
                                isRacingGamesLoading = false,
                                errorMessage = result.message
                            )
                        }
                    }
                    is Resources.Success -> {
                        val games = result.data ?: emptyList()
                        _uiState.update {
                            it.copy(
                                isRacingGamesLoading = false,
                                racingGames = games
                            )
                        }
                    }
                }
            }.launchIn(this)
        }
    }

    private fun getShooterGames(category: String = "shooter") {
        viewModelScope.launch {
            repository.getGamesByCategory(category).onEach { result ->
                when (result) {
                    is Resources.Loading -> {
                        _uiState.update {
                            it.copy(isShooterGamesLoading = true)
                        }
                    }
                    is Resources.Error -> {
                        _uiState.update {
                            it.copy(
                                isShooterGamesLoading = false,
                                errorMessage = result.message
                            )
                        }
                    }
                    is Resources.Success -> {
                        val games = result.data ?: emptyList()
                        _uiState.update {
                            it.copy(
                                isShooterGamesLoading = false,
                                shooterGames = games
                            )
                        }
                    }
                }
            }.launchIn(this)
        }
    }

    private fun getAnimeGames(category: String = "anime") {
        viewModelScope.launch {
            repository.getGamesByCategory(category).onEach { result ->
                when (result) {
                    is Resources.Loading -> {
                        _uiState.update {
                            it.copy(isAnimeGamesLoading = true)
                        }
                    }
                    is Resources.Error -> {
                        _uiState.update {
                            it.copy(
                                isAnimeGamesLoading = false,
                                errorMessage = result.message
                            )
                        }
                    }
                    is Resources.Success -> {
                        val games = result.data ?: emptyList()
                        _uiState.update {
                            it.copy(
                                isAnimeGamesLoading = false,
                                animeGames = games
                            )
                        }
                    }
                }
            }.launchIn(this)
        }
    }

}