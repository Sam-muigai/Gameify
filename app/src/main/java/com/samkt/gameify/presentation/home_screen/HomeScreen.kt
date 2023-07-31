package com.samkt.gameify.presentation.home_screen

import androidx.compose.animation.AnimatedVisibility
import com.samkt.gameify.presentation.destinations.GameScreenDestination
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.samkt.gameify.presentation.category_screen.CategoryScreen
import com.samkt.gameify.presentation.destinations.CategoryScreenDestination
import com.samkt.gameify.presentation.destinations.SearchScreenDestination
import com.samkt.gameify.presentation.home_screen.components.CategoryGames
import com.samkt.gameify.presentation.navigation.NavigationTransition
import com.samkt.gameify.presentation.search_screen.SearchScreen
import com.samkt.gameify.ui.theme.poppins

@OptIn(ExperimentalMaterial3Api::class)
@Destination(start = true,style = NavigationTransition::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val state = viewModel.uiState.collectAsState().value
    Scaffold(
        modifier = Modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)) {
                Text(
                    modifier = Modifier,
                    text = "GAMEIFY",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = poppins,
                        fontSize = 27.sp
                    )
                )
            }
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        navigator.navigate(SearchScreenDestination())
                    },
                value = "",
                onValueChange = {
                    navigator.navigate(SearchScreenDestination())
                },
                enabled = false,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },
                placeholder = {
                    Text(
                        text = "Search",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontFamily = poppins
                        )
                    )
                },
                shape = RoundedCornerShape(100)
            )
            state.errorMessage?.let {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontFamily = poppins
                        )
                    )
                }
            }
                ?:
            Column {
                CategoryGames(
                    games = state.sportsGames,
                    loading = state.isSportsGamesLoading,
                    title = "Sports",
                    onAllClicked = {
                        navigator.navigate((CategoryScreenDestination("sports")))
                    },
                    onGameClicked = {
                        navigator.navigate(GameScreenDestination(id = it))
                    }
                )
                CategoryGames(
                    games = state.shooterGames,
                    loading = state.isShooterGamesLoading,
                    title = "Shooting",
                    onAllClicked = {
                        navigator.navigate((CategoryScreenDestination("shooter")))
                    },
                    onGameClicked = {
                        navigator.navigate(GameScreenDestination(id = it))
                    }
                )
                CategoryGames(
                    games = state.animeGames,
                    loading = state.isAnimeGamesLoading,
                    title = "Anime",
                    onAllClicked = {
                        navigator.navigate((CategoryScreenDestination("anime")))
                    },
                    onGameClicked = {
                        navigator.navigate(GameScreenDestination(id = it))
                    }
                )
                CategoryGames(
                    games = state.racingGames,
                    loading = state.isRacingGamesLoading,
                    title = "Racing",
                    onAllClicked = {
                        navigator.navigate((CategoryScreenDestination("racing")))
                    },
                    onGameClicked = {
                        navigator.navigate(GameScreenDestination(id = it))
                    }
                )
            }
        }
    }
}