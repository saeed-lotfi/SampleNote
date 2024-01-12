package saeid.lotfi.samplenote.ui

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import saeid.lotfi.samplenote.ui.theme.SampleNoteTheme

@Composable
fun Main() {
    val modifier = Modifier.fillMaxSize()
    SampleNoteTheme {
        Surface(modifier = modifier) {
            MainNavigation(modifier)
        }
    }
}

@Composable
fun MainNavigation(
    modifier: Modifier,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home",
        enterTransition = { AnimationConstants.enterTransition },
        popEnterTransition = { AnimationConstants.enterTransition },
        exitTransition = { AnimationConstants.exitTransition },
        popExitTransition = { AnimationConstants.exitTransition },
    ) {
        composable(
            route = "home",
        ) {
            Home(
                modifier = modifier,
            )
        }
    }
}

object AnimationConstants {
    private const val enterMillis = 250
    private const val exitMillis = 250

    val enterTransition = fadeIn(
        animationSpec = tween(
            durationMillis = enterMillis,
            easing = FastOutLinearInEasing,
        ),
    )

    val exitTransition = fadeOut(
        animationSpec = tween(
            durationMillis = exitMillis,
            easing = FastOutLinearInEasing,
        ),
    )
}
