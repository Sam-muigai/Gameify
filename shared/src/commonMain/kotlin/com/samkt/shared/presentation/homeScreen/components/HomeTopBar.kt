package com.samkt.shared.presentation.homeScreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samkt.shared.presentation.ui.theme.poppins


@Composable
fun HomeTopBar(
    onClick: () -> Unit,
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
        ) {
            Text(
                modifier = Modifier,
                text = "GAMEIFY",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = poppins(),
                    fontSize = 27.sp,
                ),
            )
        }
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable {
                    onClick.invoke()
                },
            value = "",
            onValueChange = {
                onClick.invoke()
            },
            enabled = false,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                )
            },
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
    }
}
