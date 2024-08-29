package com.samkt.shared.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.samkt.shared.presentation.ui.theme.poppins
import com.samkt.shared.resources.Res
import com.samkt.shared.resources.ic_calendar
import com.samkt.shared.resources.ic_category
import com.skydoves.landscapist.coil3.CoilImage
import org.jetbrains.compose.resources.painterResource

@Composable
fun GameCategoryItem(
    modifier: Modifier = Modifier,
    imageUrl: String,
    title: String,
    genre: String,
    releaseDate: String,
    onClick: () -> Unit,
) {
    Surface(
        modifier = modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth()
            .border(1.dp, Color.White, RoundedCornerShape(8.dp))
            .clickable {
                onClick.invoke()
            },
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.background,
    ) {
        Row(modifier = Modifier) {
            CoilImage(
                modifier = Modifier
                    .width(120.dp)
                    .height(135.dp)
                    .clip(RoundedCornerShape(8.dp)),
                imageModel = { imageUrl },
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = poppins(),
                    ),
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                   Icon(
                        painter = painterResource(Res.drawable.ic_category),
                        contentDescription = null,
                    )
                    Text(text = genre)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_calendar),
                        contentDescription = null,
                    )
                    Text(text = releaseDate)
                }
            }
        }
    }
}