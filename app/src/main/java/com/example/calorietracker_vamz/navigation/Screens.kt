package com.example.calorietracker_vamz.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.calorietracker_vamz.R

sealed class Screens(
    val path: String,
    val icon: ImageVector?,
    @StringRes val resourceId: Int?,
) {
    data object StatisticScreen : Screens("Home", Icons.Default.Home, R.string.homebottombar)
    data object FoodScreen : Screens("Food",Icons.Default.Add, R.string.foodbottombar)
    data object EatenFoodsScreen : Screens("EatenFood", Icons.Default.Star, R.string.eatenfoodbottombar)
    data object InfoScreen : Screens("Info", null, null)


}