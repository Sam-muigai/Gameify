package com.samkt.gameify.presentation.category_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.samkt.gameify.presentation.components.GameCategoryItem
import com.samkt.gameify.presentation.destinations.GameScreenDestination
import com.samkt.gameify.presentation.navigation.NavigationTransition
import com.samkt.gameify.ui.theme.poppins

@OptIn(ExperimentalMaterial3Api::class)
@Destination(style = NavigationTransition::class)
@Composable
fun CategoryScreen(
    category: String,
    navigator: DestinationsNavigator,
    viewModel: CategoryViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState().value
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
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
                                .padding(horizontal = 8.dp)
                        ) {
                            IconButton(onClick = { navigator.popBackStack() }) {
                                Icon(
                                    modifier = Modifier.align(Alignment.CenterStart),
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "Navigate back"
                                )
                            }
                            Text(
                                modifier = Modifier.align(Alignment.Center),
                                text = category,
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontFamily = poppins,
                                    fontSize = 24.sp
                                )
                            )
                        }
                    }
                    items(state.games) {
                        GameCategoryItem(
                            imageUrl = it.thumbnail,
                            title = it.title,
                            genre = it.genre,
                            releaseDate = it.releaseDate,
                            onClick = {
                                navigator.navigate(GameScreenDestination(it.id))
                            }
                        )
                    }
                }
            )
            AnimatedVisibility(
                visible = state.isLoading,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                CircularProgressIndicator(strokeWidth = 2.dp)
            }
            state.errorMessage?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontFamily = poppins
                    )
                )
            }
        }
    }
}