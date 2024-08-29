package com.samkt.shared.presentation.categoryScreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samkt.shared.presentation.components.GameCategoryItem
import com.samkt.shared.presentation.navigation.AppRoute
import com.samkt.shared.presentation.ui.theme.poppins


@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: CategoryViewModel,
    category: String
) {
    val state = viewModel.categoryScreenUiState.collectAsState().value
    LaunchedEffect(key1 = true, block = {
        viewModel.getGames(category)
    })
    Scaffold { paddingValues ->
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                contentPadding = PaddingValues(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                content = {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp),
                        ) {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(
                                    modifier = Modifier.align(Alignment.CenterStart),
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "Navigate back",
                                )
                            }
                            Text(
                                modifier = Modifier.align(Alignment.Center),
                                text = category.replaceFirstChar {
                                    if (it.isLowerCase()) {
                                        it.titlecase()
                                    } else {
                                        it.toString()
                                    }
                                },
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontFamily = poppins(),
                                    fontSize = 24.sp,
                                ),
                            )
                        }
                    }
                    items(
                        items = state.games,
                        key = {
                            it.id
                        },
                    ) {
                        GameCategoryItem(
                            imageUrl = it.thumbnail,
                            title = it.title,
                            genre = it.genre,
                            releaseDate = it.releaseDate,
                            onClick = {
                                navController.navigate(AppRoute.GameScreen.route + "?id=${it.id}")
                            },
                        )
                    }
                },
            )
            if (state.isLoading) {
                CircularProgressIndicator(strokeWidth = 2.dp)
            }
            if (!state.errorMessage.isNullOrBlank() && state.games.isEmpty()) {
                Column(
                    modifier = Modifier.clickable { viewModel.getGames(category) },
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    // ErrorAnimation(modifier = Modifier.size(200.dp))
                    Text(
                        text = "${state.errorMessage}. Tap to retry",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontFamily = poppins(),
                        ),
                    )
                }
            }
        }
    }
}