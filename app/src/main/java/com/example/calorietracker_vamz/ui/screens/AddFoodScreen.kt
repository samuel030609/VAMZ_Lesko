package com.example.calorietracker_vamz.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.calorietracker_vamz.data.FoodRepository

@Composable
fun AddFoodScreen(foodId: Int, viewModel: EatenFoodsViewModel = viewModel())
{
    val food = FoodRepository.getFood(foodId)
    val quantity by remember { mutableStateOf("") }
}