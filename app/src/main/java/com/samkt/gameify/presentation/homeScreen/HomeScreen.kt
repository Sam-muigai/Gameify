package com.samkt.gameify.presentation.homeScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.samkt.gameify.presentation.components.ErrorAnimation
import com.samkt.gameify.presentation.components.ShimmerLoadingNow
import com.samkt.gameify.presentation.destinations.CategoryScreenDestination
import com.samkt.gameify.presentation.destinations.GameScreenDestination
import com.samkt.gameify.presentation.destinations.SearchScreenDestination
import com.samkt.gameify.presentation.homeScreen.components.CategoryGames
import com.samkt.gameify.presentation.homeScreen.components.HomeTopBar
import com.samkt.gameify.presentation.navigation.NavigationTransition
import com.samkt.gameify.ui.theme.poppins
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Destination(start = true, style = NavigationTransition::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigator: DestinationsNavigator,
) {
    val state = viewModel.homeScreenState.collectAsStateWithLifecycle().value

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center,
        ) {
            if (state.isLoading) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    content = {
                        item {
                            HomeTopBar(onClick = {})
                        }
                        items(5) {
                            ShimmerLoadingNow()
                        }
                    },
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    content = {
                        item {
                            HomeTopBar(
                                onClick = {
                                    /*
                                    * Navigate to search screen if data is retrieved else remain in home-screen
                                    * */
                                    if (state.errorMessage.isNullOrBlank()) {
                                        navigator.navigate(SearchScreenDestination)
                                    } else {
                                        return@HomeTopBar
                                    }
                                },
                            )
                        }
                        if (state.sportsGames.isNotEmpty()) {
                            item(key = 1) {
                                val sampleGame = state.sportsGames[0]
                                CategoryGames(
                                    games = state.sportsGames,
                                    title = "Sports",
                                    onAllClicked = {
                                        navigator.navigate((CategoryScreenDestination(sampleGame.genre)))
                                    },
                                    onGameClicked = {
                                        navigator.navigate(GameScreenDestination(id = it))
                                    },
                                )
                            }
                        }
                        if (state.shooterGames.isNotEmpty()) {
                            item(key = 2) {
                                val sampleGame = state.shooterGames[0]
                                CategoryGames(
                                    games = state.shooterGames,
                                    title = "Shooting",
                                    onAllClicked = {
                                        navigator.navigate((CategoryScreenDestination(sampleGame.genre)))
                                    },
                                    onGameClicked = {
                                        navigator.navigate(GameScreenDestination(id = it))
                                    },
                                )
                            }
                        }
                        if (state.fightingGames.isNotEmpty()) {
                            item(key = 3) {
                                val sampleGame = state.fightingGames[0]
                                CategoryGames(
                                    games = state.fightingGames,
                                    title = "Fighting",
                                    onAllClicked = {
                                        navigator.navigate((CategoryScreenDestination(sampleGame.genre)))
                                    },
                                    onGameClicked = {
                                        navigator.navigate(GameScreenDestination(id = it))
                                    },
                                )
                            }
                        }
                        if (state.racingGames.isNotEmpty()) {
                            item(key = 4) {
                                val sampleGame = state.racingGames[0]
                                CategoryGames(
                                    games = state.racingGames,
                                    title = "Racing",
                                    onAllClicked = {
                                        navigator.navigate((CategoryScreenDestination(sampleGame.genre)))
                                    },
                                    onGameClicked = {
                                        navigator.navigate(GameScreenDestination(id = it))
                                    },
                                )
                            }
                        }
                        /*
                        Show the category section if data is successfully retrieved
                        * */
                        if (state.errorMessage.isNullOrBlank()) {
                            item(key = 5) {
                                MoreCategories(
                                    categories = viewModel.categories,
                                    onCategoryClicked = {
                                        navigator.navigate(CategoryScreenDestination(it))
                                    },
                                )
                            }
                        }
                    },
                )
            }
            state.errorMessage?.let {
                Column(
                    modifier = Modifier.clickable { viewModel.getAllGames() },
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    ErrorAnimation(modifier = Modifier.size(200.dp))
                    Text(
                        text = "$it. Tap to retry",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontFamily = poppins,
                        ),
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MoreCategories(
    categories: List<String>,
    onCategoryClicked: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "More Categories",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    fontFamily = poppins,
                ),
            )
        }
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            maxItemsInEachRow = 3,
        ) {
            categories.forEach {
                Button(
                    modifier = Modifier.widthIn(min = 105.dp),
                    onClick = {
                        onCategoryClicked.invoke(it)
                    },
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(
                        text = it.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontFamily = poppins,
                            fontSize = 12.sp,
                        ),
                    )
                }
            }
        }
    }
}
