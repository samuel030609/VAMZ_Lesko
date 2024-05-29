@file:Suppress("NAME_SHADOWING")

package com.example.calorietracker_vamz.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.calorietracker_vamz.ui.screens.AddFoodScreen
import com.example.calorietracker_vamz.ui.screens.EatenFoodScreen
import com.example.calorietracker_vamz.ui.screens.FoodScreen
import com.example.calorietracker_vamz.ui.screens.StatisticScreen

@Composable
fun MyNavHost(
    navController: NavHostController,
) {

    NavHost(navController = navController,
        startDestination = Screens.StatisticScreen.path
    ) {
        composable(Screens.StatisticScreen.path) {
            StatisticScreen()
        }
        composable(Screens.FoodScreen.path) {
            FoodScreen(
                navigateToEatenFoodScreen = { foodId ->
                    navController.navigate(Screens.AddFoodScreen(foodId).path)
                }
            )
        }

        composable(Screens.EatenFoodsScreen.path) {
            EatenFoodScreen()
        }

        composable("AddFoodScreen/{foodId}") { backStackEntry ->
            val arguments = backStackEntry.arguments
            val foodId = arguments?.getInt("foodId")
            if (foodId != null) {
                AddFoodScreen(
                    navigateBack = { navController.popBackStack() },
                    foodId = foodId
                )
            }
        }
    }
}