package com.chyrta.livesport.navigation

sealed class LiveSportNavRoute(val route: String) {
    object List: LiveSportNavRoute("list")
    object Detail: LiveSportNavRoute("detail")
}
