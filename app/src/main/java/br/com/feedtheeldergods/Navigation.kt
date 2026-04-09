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

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val viewModel: GameViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "choose_god"
    ) {
        composable("choose_god") {
            ChooseGodScreen(
                onGodSelected = { choice ->
                    viewModel.selectGod(choice)
                    navController.navigate("hub")
                }
            )
        }

        composable("hub") {
            HubScreen(
                viewModel = viewModel,
                onFeed = { navController.navigate("feed") },
                onPlay = { navController.navigate("play") },
                onRest = { navController.navigate("rest") },
                onBathe = {
                    viewModel.bathe()
                    if (viewModel.isGameOver) navController.navigate("game_over")
                },
                onBathroom = {
                    viewModel.bathroom()
                    if (viewModel.isGameOver) navController.navigate("game_over")
                }
            )
        }

        composable("feed") {
            FeedScreen(
                viewModel = viewModel,
                onDone = {
                    if (viewModel.isGameOver) navController.navigate("game_over")
                    else navController.popBackStack()  // volta pro hub
                }
            )
        }

        composable("play") {
            PlayScreen(
                viewModel = viewModel,
                onDone = {
                    if (viewModel.isGameOver) navController.navigate("game_over")
                    else navController.popBackStack()
                }
            )
        }

        composable("rest") {
            RestScreen(
                viewModel = viewModel,
                onDone = {
                    if (viewModel.isGameOver) navController.navigate("game_over")
                    else navController.popBackStack()
                }
            )
        }

        composable("game_over") {
            GameOverScreen(
                message = viewModel.deathMessage ?: "something went wrong...",
                onRestart = {
                    navController.navigate("choose_god") {
                        popUpTo("choose_god") { inclusive = true }  // limpa o backstack
                    }
                }
            )
        }
    }
}
