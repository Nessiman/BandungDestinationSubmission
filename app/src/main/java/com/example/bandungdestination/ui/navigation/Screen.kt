package com.example.bandungdestination.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object DestinationList : Screen("DestinationList")
    object About : Screen("About")
    object Detail : Screen("home/{bandungDestinationId}"){
        fun createRoute(bandungdestinationid:Int) = "home/$bandungdestinationid"
    }
}
