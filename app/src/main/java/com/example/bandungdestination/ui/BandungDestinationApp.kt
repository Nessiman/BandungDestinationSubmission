package com.example.bandungdestination.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.Screen.DestinationList.DestinationList
import com.example.Screen.HomeScreen
import com.example.Screen.about.AboutScreen
import com.example.Screen.detail.DetailScreen
import com.example.bandungdestination.R
import com.example.bandungdestination.ui.navigation.NavigationItem
import com.example.bandungdestination.ui.navigation.Screen
import com.example.bandungdestination.ui.theme.BandungDestinationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BandungDestionationApp(
    modiier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.Detail.route){
                BottomBar(navController)
            }
        },
        modifier = modiier
    ){ innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(Screen.Home.route) {
                HomeScreen(navigateToDetail = { bandungDestinationId ->
                    navController.navigate(Screen.Detail.createRoute(bandungDestinationId))
                    }
                )
            }
            composable(Screen.DestinationList.route){
                DestinationList(navigateToDetail = { bandungDestinationId ->
                    navController.navigate(Screen.Detail.createRoute(bandungDestinationId))
                    }
                )
            }
            composable(Screen.About.route) {
                AboutScreen()
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(
                    navArgument("bandungDestinationId"){ type = NavType.IntType}
                )
            ){
                val id = it.arguments?.getInt("bandungDestinationId") ?: -1
                DetailScreen(bandungDestinationId = id,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BandungDestinationAppPreview(){
    BandungDestinationTheme {
        BandungDestionationApp()
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    modiier: Modifier = Modifier
){
    NavigationBar (
        modifier = modiier
    ){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val curremtRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),

            NavigationItem(
                title = stringResource(R.string.menu_watch_list),
                icon = Icons.Default.Favorite,
                screen = Screen.DestinationList
            ),
            NavigationItem(
                title = stringResource(R.string.menu_about),
                icon = Icons.Default.AccountBox,
                screen = Screen.About
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = curremtRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}