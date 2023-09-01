package com.samkt.gameify.presentation.searchScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samkt.gameify.domain.model.Games
import com.samkt.gameify.domain.repository.GamesRepository
import com.samkt.gameify.util.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

const val RESULT = "result"

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: GamesRepository,
) : ViewModel() {

    private var _searchScreenState = MutableStateFlow(SearchScreenStates())
    val searchScreenState = _searchScreenState.asStateFlow()

    private var cachedGames: List<Games>? = null
    private var job: Job? = null

    init {
        getAllGames()
    }

    fun onEvent(event: SearchScreenEvents) {
        when (event) {
            is SearchScreenEvents.OnValueChange -> {
                val word = event.value.lowercase()
                _searchScreenState.update {
                    it.copy(
                        searchTerm = word,
                    )
                }
                job?.cancel()
                job = viewModelScope.launch {
                    delay(500)
                    _searchScreenState.update {
                        val games = if (word.isNotEmpty()) {
                            cachedGames?.filter { game -> game.title.lowercase().contains(word) }
                                ?: emptyList()
                        } else {
                            cachedGames
                        }
                        it.copy(
                            games = games ?: emptyList(),
                        )
                    }
                }
            }
        }
    }

    private fun getAllGames() {
        viewModelScope.launch {
            _searchScreenState.update {
                it.copy(
                    isLoading = true,
                )
            }
            repository.getAllGames().onEach { result ->
                when (result) {
                    is Resources.Success -> {
                        val games = result.data
                        cachedGames = games
                        Timber.d(games.toString())
                        _searchScreenState.update {
                            it.copy(
                                isLoading = false,
                                games = games ?: emptyList(),
                            )
                        }
                    }

                    is Resources.Error -> {
                        _searchScreenState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = result.message,
                            )
                        }
                        Timber.d(result.message.toString())
                    }
                }
            }.launchIn(this)
        }
    }
}

// data class SearchScreenStates(
//    val searchTerm: String = "",
//    val games: List<Games> = emptyList(),
//    val errorMessage: String? = null,
//    val isLoading: Boolean = false,
// )

data class SearchScreenStates(
    val searchTerm: String = "",
    val games: List<Games> = emptyList(),
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
)

sealed class SearchScreenEvents {
    data class OnValueChange(val value: String) : SearchScreenEvents()
}
