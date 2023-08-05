package com.samkt.gameify.presentation.search_screen

import android.util.Log
import androidx.compose.ui.text.toLowerCase
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
import java.util.Locale
import javax.inject.Inject

const val RESULT = "result"
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: GamesRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(SearchScreenStates())
    val uiState = _uiState.asStateFlow()

    private var cachedGames:List<Games>? = null
    init {
        getAllGames()
    }

    private var job: Job? = null

    fun onEvent(event: SearchScreenEvents) {
        when (event) {
            is SearchScreenEvents.OnValueChange -> {
                val word = event.value.lowercase()
                _uiState.update {
                    it.copy(
                        searchTerm = word
                    )
                }
                job?.cancel()
                job  = viewModelScope.launch{
                    delay(500)
                    _uiState.update{
                        val games = if (word.isNotEmpty()){
                            cachedGames?.filter { game -> game.title.lowercase().contains(word) } ?: emptyList()
                        }else{
                            cachedGames
                        }
                        it.copy(
                            games = games ?: emptyList()
                        )
                    }
                }
            }
        }
    }

    private fun getAllGames() {
       viewModelScope.launch {
          repository.getAllGames().onEach { result ->
                when (result) {
                    is Resources.Success -> {
                        val games = result.data
                        cachedGames = games
                        Log.d(RESULT,games.toString())
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                games = games ?: emptyList()
                            )
                        }
                    }

                    is Resources.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = result.message
                            )
                        }
                        Log.d(RESULT,result.message.toString())
                    }

                    is Resources.Loading -> {
                        _uiState.update {
                            it.copy(
                                isLoading = true
                            )
                        }
                        Log.d(RESULT,"Loading")
                    }
                }
            }.launchIn(this)
        }
    }

}