package com.samkt.gameify.presentation.home_screen

import android.util.Log
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
import java.lang.Error
import javax.inject.Inject


const val RESULT = "result"
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: GamesRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(HomeScreenState())
    val uiState = _uiState.asStateFlow()

    init {
        getAllGames()
    }

    private fun getAllGames() {
        viewModelScope.launch {
            repository.getAllGames().onEach { result->
                when(result){
                    is Resources.Success ->{
                        val games = result.data
                        val shootingGames = games?.filter { it.genre.lowercase() == "shooter" } ?: emptyList()
                        val racingGames = games?.filter { it.genre.lowercase() == "racing" } ?: emptyList()
                        val sportsGames = games?.filter { it.genre.lowercase() == "sports" } ?: emptyList()
                        val fightingGames = games?.filter { it.genre.lowercase() == "fighting" } ?: emptyList()
                        delay(1000)
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                shooterGames = shootingGames,
                                racingGames = racingGames,
                                sportsGames = sportsGames,
                                fightingGames = fightingGames
                            )
                        }
                        Log.d(RESULT,(result.data ?: emptyList()).toString())
                    }
                    is Resources.Error ->{
                        val errorMessage = result.message ?: "Error occurred"
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = errorMessage
                            )
                        }
                        Log.d(RESULT, errorMessage)
                    }
                    is Resources.Loading->{
                        _uiState.update {
                            it.copy(
                                isLoading = true
                            )
                        }
                        Log.d(RESULT,"loading")
                    }
                }
            }.launchIn(this)
        }
    }


}