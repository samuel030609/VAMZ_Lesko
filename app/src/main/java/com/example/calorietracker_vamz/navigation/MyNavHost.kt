package com.example.calorietracker_vamz.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.calorietracker_vamz.ui.screens.FoodScreen
import com.example.calorietracker_vamz.ui.screens.StatisticScreen

@Composable
fun MyNavHost(
    navController: NavHostController
) {

    NavHost(navController = navController,
        startDestination = Screens.StatisticScreen.path
    ) {
        composable(Screens.StatisticScreen.path) {
            StatisticScreen()
        }
        composable(Screens.FoodScreen.path) {
            FoodScreen()
        }
    }
}