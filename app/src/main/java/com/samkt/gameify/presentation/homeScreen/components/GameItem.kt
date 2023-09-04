package com.samkt.gameify.presentation.homeScreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samkt.gameify.ui.theme.GameifyTheme
import com.samkt.gameify.ui.theme.poppins
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun GameItem(
    modifier: Modifier = Modifier,
    title: String,
    imageUrl: String,
    onGameClicked: () -> Unit,
) {
    val context = LocalContext.current
    Column(
        modifier = modifier.width(130.dp).padding(horizontal = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CoilImage(
            imageModel = imageUrl,
            shimmerParams = ShimmerParams(
                baseColor = MaterialTheme.colorScheme.background,
                highlightColor = MaterialTheme.colorScheme.onBackground,
                durationMillis = 1000,
                tilt = 25F,
                dropOff = 0.65f,
            ),
            circularReveal = CircularReveal(duration = 500),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    onGameClicked.invoke()
                }
                .height(160.dp)
                .width(120.dp)
                .clip(RoundedCornerShape(10.dp)),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold,
                fontFamily = poppins,
                fontSize = 12.sp,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
fun GameItemPreview() {
    GameifyTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            GameItem(
                modifier = Modifier.padding(16.dp),
                title = "OverWatch 2",
                imageUrl = "https://www.freetogame.com/g/540/thumbnail.jpg",
            ) {
            }
        }
    }
}
