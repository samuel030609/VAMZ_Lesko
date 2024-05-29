package com.example.calorietracker_vamz.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calorietracker_vamz.ViewModelInitializer
import com.example.calorietracker_vamz.data.Food

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodScreen(
    navigateToEatenFoodScreen: (Int) -> Unit,
    viewModel: FoodScreenViewModel = viewModel(factory = ViewModelInitializer.Factory)
) {
    val foodUiState by viewModel.foodUiState.collectAsState()

    val foods = foodUiState.foods

    FoodBody(
        foods = foods,
        onFoodClick = { foodId -> navigateToEatenFoodScreen(foodId) }
    )

}

@Composable
fun FoodBody(
    foods: List<Food>,
    onFoodClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if(foods.isEmpty()) {
            Text(
                text = "No food items found",
                fontSize = 20.sp
            )
        } else {
            FoodList(
                foods = foods,
                onFoodClick = onFoodClick,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }

}

@Composable
fun FoodList(
    foods: List<Food>,
    onFoodClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn (
        modifier = modifier
    ) {

        items(items = foods, key = { food -> food.id }) { food ->
            FoodItem(
                modifier = Modifier.padding(8.dp),
                id = food.id.toString(),
                name = food.name,
                calories = food.calories.toString(),
                protein = food.protein.toString(),
                carbs = food.carbs.toString(),
                fat = food.fat.toString(),
                sugar = food.sugar.toString(),
                onClick = { onFoodClick(food.id) }
            )
        }
    }
}

@Composable
fun FoodItem(modifier: Modifier = Modifier, id: String = "", name: String = "", calories: String = "", protein: String = "", carbs: String = "", fat: String = "", sugar: String = "", onClick: () -> Unit) {
    Card(
        modifier = modifier.clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {

        Column (modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        )
            {
                Text(
                    text = "Name: $name",
                    fontSize = 20.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "ID: $id",
                        fontSize = 20.sp
                    )

                    Text(
                        text = "Calories: $calories",
                        fontSize = 20.sp
                    )

                    Text(
                        text = "Protein: $protein",
                        fontSize = 20.sp
                    )
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Carbs: $carbs",
                        fontSize = 20.sp
                    )
                    Text(
                        text = "Fat: $fat",
                        fontSize = 20.sp
                    )
                    Text(
                        text = "Sugar: $sugar",
                        fontSize = 20.sp
                    )
                }
            }
    }
}

