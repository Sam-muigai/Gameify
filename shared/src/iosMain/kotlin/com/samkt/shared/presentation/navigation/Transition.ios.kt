package com.samkt.shared.presentation.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

actual fun NavGraphBuilder.destination(
    route: String,
    arguments: List<NamedNavArgument>,
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        arguments = arguments,
        content = content,
        enterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start)
        },
        exitTransition = {
            fadeOut(
                animationSpec = tween(delayMillis = 10),
            )
        },
        popEnterTransition = {
            EnterTransition.None
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(delayMillis = 10),
            )
        },
    )
}