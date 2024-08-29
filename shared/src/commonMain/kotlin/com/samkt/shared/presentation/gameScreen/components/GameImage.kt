package com.samkt.shared.presentation.gameScreen.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.samkt.shared.domain.model.ScreenShot
import com.skydoves.landscapist.coil3.CoilImage
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@Composable
fun GameImage(
    modifier: Modifier = Modifier,
    screenShots: List<ScreenShot>,
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = {
            screenShots.size
        }
    )
    val scope = rememberCoroutineScope()
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp),
        color = MaterialTheme.colorScheme.background.copy(
            0.3f,
        ),
    ) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator(strokeWidth = 2.dp)
            HorizontalPager(state = pagerState) {
                CoilImage(
                    imageModel = {
                        screenShots[it].image
                    },
                    modifier = Modifier.fillMaxSize(),
                )
            }
            if (pagerState.currentPage != screenShots.size - 1 && screenShots.isNotEmpty()) {
                IconButton(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    },
                ) {
                    Icon(
                        modifier = Modifier.size(35.dp),
                        imageVector = Icons.Default.KeyboardArrowRight,
                        tint = Color.Black,
                        contentDescription = null,
                    )
                }
            }

            if (pagerState.currentPage != 0 && screenShots.isNotEmpty()) {
                IconButton(
                    modifier = Modifier.align(Alignment.CenterStart),
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    },
                ) {
                    Icon(
                        modifier = Modifier.size(35.dp),
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        tint = Color.Black,
                        contentDescription = null,
                    )
                }
            }
            Box(
                modifier = Modifier
                    .height(25.dp)
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colorScheme.background,
                        RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                    )
                    .align(Alignment.BottomCenter),
            )
        }
    }
}