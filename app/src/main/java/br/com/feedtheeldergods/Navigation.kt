package br.com.feedtheeldergods

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.feedtheeldergods.ui.theme.ChooseGodScreen
import br.com.feedtheeldergods.ui.theme.FeedScreen
import br.com.feedtheeldergods.ui.theme.GameOverScreen
import br.com.feedtheeldergods.ui.theme.HubScreen
import br.com.feedtheeldergods.ui.theme.PlayScreen
import br.com.feedtheeldergods.ui.theme.RestScreen
import br.com.feedtheeldergods.ui.theme.SoulFeedMinigame

object Screen{
    const val CHOOSE_GOD = "choose_god"
    const val HUB = "hub"
    const val FEED = "feed"
    const val FEED_SOULS    = "feed_souls"
    const val PLAY = "play"
    const val REST = "rest"
    const val GAME_OVER = "game_over"
}
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val viewModel: GameViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.CHOOSE_GOD
    ) {
        composable(Screen.CHOOSE_GOD) {
            ChooseGodScreen(
                onGodSelected = { choice ->
                    viewModel.selectGod(choice)
                    navController.navigate(Screen.HUB)
                }
            )
        }

        composable(Screen.HUB) {
            HubScreen(
                viewModel = viewModel,
                onFeed = { navController.navigate(Screen.FEED) },
                onPlay = { navController.navigate(Screen.PLAY) },
                onRest = { navController.navigate(Screen.REST) },
                onBathe = {},
            ) {
                viewModel.bathe()
                if (viewModel.isGameOver) navController.navigate(Screen.GAME_OVER)
            }
        }

        composable(Screen.FEED) {
            FeedScreen(
                viewModel = viewModel,
                onSouls = {navController.navigate(Screen.FEED_SOULS)},
                onDone = {
                    if (viewModel.isGameOver) navController.navigate(Screen.GAME_OVER)
                    else navController.popBackStack()
                }
            )
        }

        composable(Screen.PLAY) {
            PlayScreen(
                viewModel = viewModel,
                onDone = {
                    if (viewModel.isGameOver) navController.navigate(Screen.GAME_OVER)
                    else navController.popBackStack()
                }
            )
        }

        composable(Screen.FEED_SOULS) {
            SoulFeedMinigame(
                viewModel = viewModel,
                onDone = {
                    if (viewModel.isGameOver) navController.navigate(Screen.GAME_OVER)
                    else navController.navigate(Screen.HUB) {
                        popUpTo(Screen.HUB) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.REST) {
            RestScreen(
                viewModel = viewModel,
                onDone = {
                    if (viewModel.isGameOver) navController.navigate(Screen.GAME_OVER)
                    else navController.popBackStack()
                }
            )
        }

        composable(Screen.GAME_OVER) {
            GameOverScreen(
                message = viewModel.deathMessage ?: "something went wrong...",
                onRestart = {
                    navController.navigate(Screen.CHOOSE_GOD) {
                        popUpTo(Screen.CHOOSE_GOD) { inclusive = true }
                    }
                }
            )
        }
    }
}
