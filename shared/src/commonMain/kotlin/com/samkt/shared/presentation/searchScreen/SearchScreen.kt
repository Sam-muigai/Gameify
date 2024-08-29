package com.samkt.shared.presentation.searchScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.samkt.shared.presentation.components.GameCategoryItem
import com.samkt.shared.presentation.navigation.AppRoute
import com.samkt.shared.presentation.ui.theme.poppins

@Composable
fun SearchScreen(navController: NavController, viewModel: SearchViewModel) {
    val state = viewModel.searchScreenState.collectAsState().value

    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(strokeWidth = 2.dp)
            }
        }
        Column(modifier = Modifier.padding(paddingValues)) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value = state.searchTerm,
                onValueChange = {
                    viewModel.onEvent(SearchScreenEvents.OnValueChange(it))
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                    )
                },
                singleLine = true,
                placeholder = {
                    Text(
                        text = "Search",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontFamily = poppins(),
                        ),
                    )
                },
                shape = RoundedCornerShape(100),
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn (
                contentPadding = PaddingValues(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                content = {
                    items(state.games) {
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
            state.errorMessage?.let {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(text = it)
                }
            }
        }
    }
}