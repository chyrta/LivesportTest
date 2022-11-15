package com.chyrta.livesport.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chyrta.livesport.search.ui.SearchScreen

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
            SearchScreen()
        }
        composable(LiveSportNavRoute.Detail.route) {
            SearchScreen()
        }
    }
}
