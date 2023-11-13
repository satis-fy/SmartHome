package com.example.smart_home.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.smart_home.ui.home.HomeScreen
import com.example.smart_home.ui.search.SearchDeviceList

@Composable
fun Navigation(
     navController : NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable(route = "home") {
            HomeScreen(navController = navController)
        }

        composable(route = "search") {
            SearchDeviceList(navController = navController)
        }
    }
}