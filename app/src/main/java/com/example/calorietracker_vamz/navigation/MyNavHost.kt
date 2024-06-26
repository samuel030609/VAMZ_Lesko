package com.example.calorietracker_vamz.navigation

import android.annotation.SuppressLint
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
import com.example.calorietracker_vamz.ui.screens.InfoScreen
import com.example.calorietracker_vamz.ui.screens.StatisticScreen


//Composable, ktoré riadi navigáciu v mojej aplikácií
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MyNavHost(
    navController: NavHostController,
    startDestination: String

) {


    NavHost(navController = navController,
        startDestination = startDestination
    ) {

        composable(Screens.InfoScreen.path) {
            InfoScreen(
                navigateToStatisticScreen = {
                    navController.navigate(Screens.StatisticScreen.path)
                }
            )
        }

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
            AddFoodScreen(navigateBack = { navController.navigate(Screens.FoodScreen.path) })
        }
    }
}