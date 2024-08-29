package com.samkt.shared.presentation.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
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
            scaleInEnterTransition()
        },
        exitTransition = {
            scaleOutExitTransition()
        },
        popEnterTransition = {
            scaleInPopEnterTransition()
        },
        popExitTransition = {
            scaleOutPopExitTransition()
        },
    )
}


fun scaleInEnterTransition() = scaleIn(
    initialScale = .9f,
    animationSpec = tween(500)
) + fadeIn(
    animationSpec = tween(300)
)


fun scaleOutExitTransition() = scaleOut(
    targetScale = 1.1f,
    animationSpec = tween(300)
) + fadeOut(
    animationSpec = tween(300)
)


fun scaleInPopEnterTransition() = scaleIn(
    initialScale = 1.1f,
    animationSpec = tween(500)
) + fadeIn(
    animationSpec = tween(300)
)


fun scaleOutPopExitTransition() = scaleOut(
    targetScale = .9f,
    animationSpec = tween(300)
) + fadeOut(
    animationSpec = tween(300)
)