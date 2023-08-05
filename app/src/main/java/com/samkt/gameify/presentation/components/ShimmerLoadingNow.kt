package com.samkt.gameify.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun ShimmerLoadingNow() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)) {
        Box(
            modifier = Modifier
                .width(30.dp)
                .heightIn(10.dp)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        )
        {
            Column(modifier = Modifier
                .height(130.dp)
                .width(120.dp)
                .shimmerEffect()){}
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier
                .height(130.dp)
                .width(120.dp)
                .shimmerEffect()){}
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier
                .height(130.dp)
                .fillMaxWidth()
                .shimmerEffect()){}
        }
    }
}
