package com.samkt.shared.presentation.homeScreen

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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.samkt.shared.presentation.homeScreen.components.CategoryGames
import com.samkt.shared.presentation.homeScreen.components.HomeTopBar
import com.samkt.shared.presentation.ui.theme.poppins
import com.moriatsushi.insetsx.systemBars
import com.moriatsushi.insetsx.systemBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.WindowInsets
import com.moriatsushi.insetsx.ExperimentalSoftwareKeyboardApi
import com.moriatsushi.insetsx.safeArea
import com.moriatsushi.insetsx.safeDrawing
import com.moriatsushi.insetsx.safeDrawingPadding
import com.samkt.shared.presentation.navigation.AppRoute


@OptIn(ExperimentalSoftwareKeyboardApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navController: NavHostController,
) {
    val state = viewModel.homeScreenState.collectAsState().value

    Scaffold(
        modifier = Modifier.fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeArea),
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
                            //   ShimmerLoadingNow()
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
                                        navController.navigate(AppRoute.SearchScreen.route)
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
                                        navController.navigate(AppRoute.CategoryScreen.route + "?category=${sampleGame.genre}")
                                    },
                                    onGameClicked = {
                                        navController.navigate(AppRoute.GameScreen.route + "?id=${sampleGame.id}")
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
                                        navController.navigate(AppRoute.CategoryScreen.route + "?category=${sampleGame.genre}")
                                    },
                                    onGameClicked = {
                                        navController.navigate(AppRoute.GameScreen.route + "?id=${sampleGame.id}")
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
                                        navController.navigate(AppRoute.CategoryScreen.route + "?category=${sampleGame.genre}")
                                    },
                                    onGameClicked = {
                                        navController.navigate(AppRoute.GameScreen.route + "?id=${sampleGame.id}")
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
                                        navController.navigate(AppRoute.CategoryScreen.route + "?category=${sampleGame.genre}")
                                    },
                                    onGameClicked = {
                                        navController.navigate(AppRoute.GameScreen.route + "?id=${sampleGame.id}")
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
                                        navController.navigate(AppRoute.CategoryScreen.route + "?category=${it}")
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
                    // ErrorAnimation(modifier = Modifier.size(200.dp))
                    Text(
                        text = "$it. Tap to retry",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontFamily = poppins(),
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
                    fontFamily = poppins(),
                ),
            )
        }
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
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
                        text = it.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() },
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontFamily = poppins(),
                            fontSize = 12.sp,
                        ),
                    )
                }
            }
        }
    }
}