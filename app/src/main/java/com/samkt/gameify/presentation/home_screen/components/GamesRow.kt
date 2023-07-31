package com.samkt.gameify.presentation.home_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.samkt.gameify.domain.model.Games

@Composable
fun GamesRow(
    modifier: Modifier = Modifier,
    games: List<Games>,
    onGameClicked: (Int) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            content = {
                items(games) {
                    GameItem(
                        title = it.title,
                        imageUrl = it.thumbnail
                    ) {
                        onGameClicked(it.id)
                    }
                }
            }
        )
    }
}
