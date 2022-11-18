package com.chyrta.livesport.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chyrta.livesport.search.logic.presentation.SearchViewModel
import com.chyrta.livesport.search.ui.SearchScreen
import kiwi.orbit.compose.ui.controls.Text
import org.koin.androidx.compose.get

@Composable
fun LiveSportNavGraph(
    startDestination: String = LiveSportNavRoute.List.route
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(LiveSportNavRoute.List.route) {
            val viewModel: SearchViewModel = get()
            SearchScreen(
                viewModel = viewModel,
                onNavigateToDetail = {
                    navController.navigate(LiveSportNavRoute.Detail.route)
                }
            )
        }
        composable(LiveSportNavRoute.Detail.route) {
            Text("Empty detail screen")
        }
    }
}
