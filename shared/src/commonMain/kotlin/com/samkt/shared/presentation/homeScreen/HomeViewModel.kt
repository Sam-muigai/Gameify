package com.samkt.shared.presentation.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samkt.shared.domain.model.Games
import com.samkt.shared.domain.repository.GamesRepository
import com.samkt.shared.util.Resources
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

const val RESULT = "result"

class HomeViewModel(
    private val repository: GamesRepository,
) : ViewModel() {

    private var _homeScreenState = MutableStateFlow(HomeScreenState())
    val homeScreenState = _homeScreenState.asStateFlow()

    init {
        getAllGames()
    }

    fun getAllGames() {
        viewModelScope.launch {
            _homeScreenState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null
                )
            }
            repository.getAllGames().onEach { result ->
                when (result) {
                    is Resources.Success -> {
                        val games = result.data ?: emptyList()
                        val shootingGames = games.filter { it.genre.lowercase() == "shooter" }
                        val racingGames = games.filter { it.genre.lowercase() == "racing" }
                        val sportsGames = games.filter { it.genre.lowercase() == "sports" }
                        val fightingGames = games.filter { it.genre.lowercase() == "fighting" }
                        _homeScreenState.update {
                            it.copy(
                                isLoading = false,
                                shooterGames = shootingGames,
                                racingGames = racingGames,
                                sportsGames = sportsGames,
                                fightingGames = fightingGames,
                            )
                        }
                    }

                    is Resources.Error -> {
                        val errorMessage = result.message!!
                        _homeScreenState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = errorMessage,
                            )
                        }
                    }
                }
            }.launchIn(this)
        }
    }

    val categories = listOf(
        "mmorpg",
        "strategy",
        "moba",
        "social",
        "sandbox",
        "survival",
        "pvp",
        "pve",
        "pixel",
        "voxel",
        "zombie",
        "anime",
        "fantasy",
        "sci-fi",
        "fighting",
        "action",
        "military",
        "horror",
        "flight",
        "mmorts",
        "anime",
    )
}

data class HomeScreenState(
    val isLoading: Boolean = false,
    val shooterGames: List<Games> = emptyList(),
    val fightingGames: List<Games> = emptyList(),
    val racingGames: List<Games> = emptyList(),
    val sportsGames: List<Games> = emptyList(),
    val errorMessage: String? = null,
)
