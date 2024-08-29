package com.samkt.shared.presentation.gameScreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.samkt.shared.presentation.gameScreen.components.GameImage
import com.samkt.shared.presentation.ui.theme.poppins
import com.samkt.shared.resources.Res
import com.samkt.shared.resources.ic_calendar
import com.samkt.shared.util.SharedFunc
import org.jetbrains.compose.resources.painterResource


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameScreen(
    id: Int,
    viewModel: GameViewModel,
    navController: NavController,
) {
    val state = viewModel.gameScreenState.collectAsState().value

    LaunchedEffect(
        key1 = true,
        block = {
            viewModel.getGame(id)
        },
    )

    Scaffold(
        modifier = Modifier,
        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
    ) { paddingValues ->
        state.errorMessage?.let {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Column(
                    modifier = Modifier.clickable { viewModel.getGame(id) },
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "$it. Tap to retry",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontFamily = poppins(),
                        ),
                    )
                }
            }
        }
        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(strokeWidth = 2.dp)
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState()),
            ) {
                val screenshots = state.data?.screenshots ?: emptyList()

                if (state.errorMessage.isNullOrBlank()) {
                    Box {
                        GameImage(
                            screenShots = screenshots,
                        )
                        Column(
                            modifier = Modifier.align(Alignment.TopStart).safeDrawingPadding()
                                .padding(start = 8.dp)
                        ) {
                            FloatingActionButton(
                                modifier = Modifier.size(40.dp),
                                onClick = { navController.popBackStack() },
                                shape = CircleShape,
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "navigate back",
                                )
                            }
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp),
                ) {
                    state.data?.let {
                        val requirements = it.minimumSystemRequirements
                        Text(
                            text = it.title,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontFamily = poppins(),
                                fontWeight = FontWeight.Bold,
                            ),
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.ic_calendar),
                                contentDescription = "release_date",
                            )
                            Text(
                                text = it.releaseDate,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontFamily = poppins(),
                                ),
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            Button(
                                onClick = {
                                    SharedFunc.getGame(it.gameUrl)
                                },
                            ) {
                                Text(
                                    text = "GET GAME",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontFamily = poppins(),
                                    ),
                                )
                            }
                            Button(
                                onClick = {
                                    // TODO: Implement explicit intent
                                    val message = "Check out this game 👉 ${it.gameUrl}"
                                    SharedFunc.shareMessage(message)
                                },
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Share,
                                    contentDescription = "Share",
                                )
                                Text(
                                    text = "SHARE",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontFamily = poppins(),
                                    ),
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = it.description,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontFamily = poppins(),
                            ),
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Details",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontFamily = poppins(),
                                fontWeight = FontWeight.ExtraBold,
                            ),
                        )
                        Text(
                            text = "Genre : ${it.genre}",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontFamily = poppins(),
                            ),
                        )
                        Text(
                            text = "Platform : ${it.platform}",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontFamily = poppins(),
                            ),
                        )
                        Text(
                            text = "Publisher : ${it.publisher}",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontFamily = poppins(),
                            ),
                        )
                        Text(
                            text = "Developer : ${it.developer}",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontFamily = poppins(),
                            ),
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        requirements?.let { requirement ->
                            // Some response have an empty minimumSystemRequirement block
                            requirement.os?.let {
                                Text(
                                    text = "Minimum System Requirement",
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        fontFamily = poppins(),
                                        fontWeight = FontWeight.ExtraBold,
                                    ),
                                )
                                Text(
                                    text = "Operating System : ${requirements.os}",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontFamily = poppins(),
                                    ),
                                )
                                Text(
                                    text = "Processor : ${requirements.processor}",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontFamily = poppins(),
                                    ),
                                )
                                Text(
                                    text = "Operating System : ${requirements.os}",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontFamily = poppins(),
                                    ),
                                )
                                Text(
                                    text = "Graphics : ${requirements.graphics}",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontFamily = poppins(),
                                    ),
                                )
                                Text(
                                    text = "Minimum storage: ${requirements.storage}",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontFamily = poppins(),
                                    ),
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}