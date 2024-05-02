package com.example.calorietracker_vamz.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calorietracker_vamz.ViewModelInitializer
import com.example.calorietracker_vamz.data.Food

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodScreen(
    viewModel: FoodScreenViewModel = viewModel(factory = ViewModelInitializer.Factory)
) {
    val foodUiState by viewModel.foodUiState.collectAsState()

    val foods = foodUiState.foods

    FoodBody(foods)

}

@Composable
fun FoodBody(
    foods: List<Food>
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(foods.isEmpty()) {
            Text(
                text = "No food items found",
                fontSize = 20.sp
            )
        } else {
            FoodList(foods)
        }
    }

}

@Composable
fun FoodList(
    foods: List<Food>
) {
    LazyColumn {

        foods.forEach { food ->
            item {
                FoodItem(
                    id = food.id.toString(),
                    name = food.name,
                    calories = food.calories.toString(),
                    protein = food.protein.toString(),
                    carbs = food.carbs.toString(),
                    fat = food.fat.toString(),
                    sugar = food.sugar.toString()
                )
            }
        }

    }
}

@Composable
fun FoodItem(id: String = "", name: String = "", calories: String = "", protein: String = "", carbs: String = "", fat: String = "", sugar: String = "") {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, MaterialTheme.shapes.medium)
    ) {
        Text(
            text = "ID: $id",
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.TopStart)
        )
        Text(
            text = "Name: $name",
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.TopCenter)
        )
        Text(
            text = "Calories: $calories",
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.TopEnd)
        )
        Text(
            text = "Protein: $protein",
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.CenterStart)
        )
        Text(
            text = "Carbs: $carbs",
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.Center)
        )
        Text(
            text = "Fat: $fat",
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
        Text(
            text = "Sugar: $sugar",
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.BottomStart)
        )
    }
}

