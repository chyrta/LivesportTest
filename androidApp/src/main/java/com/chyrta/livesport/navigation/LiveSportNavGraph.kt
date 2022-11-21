package com.chyrta.livesport.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chyrta.livesport.search.logic.presentation.SearchDetailContract
import com.chyrta.livesport.search.logic.presentation.SearchDetailViewModel
import com.chyrta.livesport.search.logic.presentation.SearchViewModel
import com.chyrta.livesport.search.ui.SearchItemDetailScreen
import com.chyrta.livesport.search.ui.SearchScreen
import org.koin.androidx.compose.get

@Composable
fun LiveSportNavGraph(
    startDestination: String = LiveSportNavRoute.List.route
) {
    val navController = rememberNavController()
    val searchDetailViewModel: SearchDetailViewModel = get()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(LiveSportNavRoute.List) {
            val viewModel: SearchViewModel = get()
            SearchScreen(
                viewModel = viewModel,
                onNavigateToDetail = {
                    navController.navigate(LiveSportNavRoute.Detail.route)
                    searchDetailViewModel.setEvent(SearchDetailContract.Event.GetResultItem(it))
                }
            )
        }
        composable(LiveSportNavRoute.Detail) {
            SearchItemDetailScreen(viewModel = searchDetailViewModel)
        }
    }
}

private fun NavGraphBuilder.composable(
    navItem: LiveSportNavRoute,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navItem.route
    ) {
        content(it)
    }
}
