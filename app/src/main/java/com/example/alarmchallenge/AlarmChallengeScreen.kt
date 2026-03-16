package com.example.alarmchallenge

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alarmchallenge.ui.ChronoViewModel

import com.example.alarmchallenge.ui.StartChronoScreen
import com.example.alarmchallenge.ui.StartGameScreen

/**
 * enum values that represent the screens in the app
 */
enum class Screen(@StringRes val title: Int) {
    Chrono(title = R.string.chrono),
    Game(title = R.string.game)
}

@Composable
fun AlarmChallengeApp(
    chronoViewModel: ChronoViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = Screen.entries.find { it.name == backStackEntry?.destination?.route } ?: Screen.Chrono

    Scaffold(
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Screen.Chrono.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(route = Screen.Chrono.name) {
                StartChronoScreen(
                    navigateToGame = {
                        navController.navigate(Screen.Game.name)
                    }
                )
            }
            composable(route = Screen.Game.name) {
                StartGameScreen(
                    navigateToChrono = {
                        navController.navigate(Screen.Chrono.name)
                    }
                )
            }
        }
    }
}
