package com.samkt.shared

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.samkt.shared.di.AppContainer
import com.samkt.shared.di.AppContainerImpl
import com.samkt.shared.presentation.navigation.AppNavigationGraph
import com.samkt.shared.presentation.ui.theme.GameifyTheme

@Composable
fun App() {
    val appContainer: AppContainer by lazy {
        AppContainerImpl()
    }
    GameifyTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            AppNavigationGraph(appContainer)
        }
    }
}