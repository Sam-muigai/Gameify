package com.samkt.gameify.presentation.search_screen

import android.util.Log
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    init {
        getAllGames(searchTerm = "")
    }

    private var job: Job? = null

    fun onEvent(event: SearchScreenEvents) {
        when (event) {
            is SearchScreenEvents.OnValueChange -> {
                _uiState.update {
                    it.copy(
                        searchTerm = event.value
                    )
                }
                job?.cancel()
                job = viewModelScope.launch {
                    delay(500)
                    getAllGames(searchTerm = uiState.value.searchTerm)
                }
                Log.d(RESULT,"Typing")
            }
        }
    }

    private fun getAllGames(searchTerm: String) {
       viewModelScope.launch {
          repository.getAllGames().onEach { result ->
                when (result) {
                    is Resources.Success -> {
                        val games = if (searchTerm.isNotEmpty())
                            result.data?.filter {
                                it.title.lowercase(Locale.ROOT).contains(searchTerm.lowercase(Locale.ROOT))
                            }
                        else
                            result.data

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