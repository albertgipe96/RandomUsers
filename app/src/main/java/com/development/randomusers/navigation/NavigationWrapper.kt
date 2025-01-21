package com.development.randomusers.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.development.feature.randomusers.api.navigation.RandomUsersGraph
import com.development.feature.randomusers.api.navigation.RandomUsersNavRoute

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = RandomUsersNavRoute.List
    ) {
        RandomUsersGraph(navController = navController)
    }
}