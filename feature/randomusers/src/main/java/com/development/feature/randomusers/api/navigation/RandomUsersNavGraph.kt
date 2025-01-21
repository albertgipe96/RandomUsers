package com.development.feature.randomusers.api.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.development.feature.randomusers.impl.presentation.screens.RandomUserDetailScreen
import com.development.feature.randomusers.impl.presentation.screens.RandomUsersListScreen

fun NavGraphBuilder.RandomUsersGraph(
    navController: NavController
) {
    composable<RandomUsersNavRoute.List> {
        RandomUsersListScreen(
            onRandomUserClick = { navController.navigate(RandomUsersNavRoute.Detail(it)) }
        )
    }
    composable<RandomUsersNavRoute.Detail> {
        RandomUserDetailScreen(
            onBack = { navController.navigateUp() }
        )
    }
}