@file:Suppress("NAME_SHADOWING")

package com.example.calorietracker_vamz.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.calorietracker_vamz.ui.screens.AddFoodDestination
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
                navigateToEatenFoodScreen = {
                    navController.navigate("${AddFoodDestination.path}/$it")
                }
            )
        }

        composable(Screens.EatenFoodsScreen.path) {
            EatenFoodScreen()
        }

        composable(
            route = AddFoodDestination.pathWithArgs,
            arguments = listOf(navArgument(AddFoodDestination.foodIdKey) {
                type = NavType.IntType
            })
        ) {
            AddFoodScreen(navigateBack = { navController.popBackStack() })
        }
    }
}