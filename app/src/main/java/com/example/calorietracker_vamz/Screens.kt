package com.example.calorietracker_vamz

sealed class Screens(
    val path: String
) {
    data object Home : Screens("Home")
    data object Food : Screens("Food")
}