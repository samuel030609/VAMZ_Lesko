package com.example.calorietracker_vamz

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalorieTrackerApp() {
    val navController: NavHostController = rememberNavController()
    val topLevelDestination = listOf(Screens.Home.path, Screens.Food.path)
}
