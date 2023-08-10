package com.samkt.gameify.presentation.game_screen

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.samkt.gameify.R
import com.samkt.gameify.presentation.game_screen.components.GameImage
import com.samkt.gameify.presentation.navigation.NavigationTransition
import com.samkt.gameify.ui.theme.poppins
import com.samkt.gameify.util.shareMessage

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Destination(style = NavigationTransition::class)
@Composable
fun GameScreen(
    id: Int,
    viewModel: GameViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.uiState.collectAsState().value
    Scaffold(
        modifier = Modifier,
        contentWindowInsets = WindowInsets(0.dp,0.dp,0.dp,0.dp)
    ) { paddingValues ->
        state.errorMessage?.let {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = it)
            }
        }

        AnimatedVisibility(
            visible = state.isLoading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(strokeWidth = 2.dp)
            }
        }
        AnimatedVisibility(
            visible = !state.isLoading,
            enter = fadeIn(), exit = fadeOut()
        ) {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                val screenshots = state.data?.screenshots ?: emptyList()
                GameImage(
                    screenShots = screenshots,
                    context = context
                )
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
                                fontFamily = poppins,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_calendar),
                                contentDescription = "release_date"
                            )
                            Text(
                                text = it.releaseDate,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontFamily = poppins
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = {
                                    startActivity(
                                        context,
                                        Intent(ACTION_VIEW, Uri.parse(it.gameUrl)),
                                        null
                                    )
                                }
                            ) {
                                Text(
                                    text = "GET GAME",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontFamily = poppins
                                    )
                                )
                            }
                            Button(
                                onClick = {
                                    // TODO: Implement explicit intent
                                    val message = "Check out this game 👉 ${it.gameUrl}"
                                    shareMessage(context,message)
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Share,
                                    contentDescription = "Share"
                                )
                                Text(
                                    text = "SHARE",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontFamily = poppins
                                    )
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = it.description,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontFamily = poppins
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Details",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontFamily = poppins,
                                fontWeight = FontWeight.ExtraBold
                            )
                        )
                        Text(
                            text = "Genre : ${it.genre}",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontFamily = poppins
                            )
                        )
                        Text(
                            text = "Platform : ${it.platform}",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontFamily = poppins
                            )
                        )
                        Text(
                            text = "Publisher : ${it.publisher}",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontFamily = poppins
                            )
                        )
                        Text(
                            text = "Developer : ${it.developer}",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontFamily = poppins
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        requirements?.let { requirement ->
                            //Some response have an empty minimumSystemRequirement block
                            requirement.os?.let {
                                Text(
                                    text = "Minimum System Requirement",
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        fontFamily = poppins,
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                )
                                Text(
                                    text = "Operating System : ${requirements.os}",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontFamily = poppins
                                    )
                                )
                                Text(
                                    text = "Processor : ${requirements.processor}",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontFamily = poppins
                                    )
                                )
                                Text(
                                    text = "Operating System : ${requirements.os}",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontFamily = poppins
                                    )
                                )
                                Text(
                                    text = "Graphics : ${requirements.graphics}",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontFamily = poppins
                                    )
                                )
                                Text(
                                    text = "Minimum storage: ${requirements.storage}",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontFamily = poppins
                                    )
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