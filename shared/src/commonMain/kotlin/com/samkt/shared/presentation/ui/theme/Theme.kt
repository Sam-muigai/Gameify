package com.samkt.shared.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.moriatsushi.insetsx.rememberWindowInsetsController


private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    background = LightPurple,
)



@Composable
fun GameifyTheme(
    content: @Composable () -> Unit,
) {
    val windowInsetsController = rememberWindowInsetsController()
    LaunchedEffect(Unit) {
        windowInsetsController?.setStatusBarContentColor(dark = false)
        windowInsetsController?.setNavigationBarsContentColor(dark = false)
    }
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content,
    )
}
