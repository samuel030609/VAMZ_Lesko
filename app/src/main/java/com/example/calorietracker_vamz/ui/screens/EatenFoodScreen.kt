package com.example.calorietracker_vamz.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calorietracker_vamz.ViewModelInitializer
import com.example.calorietracker_vamz.data.Food

@Composable
fun EatenFoodScreen(viewModel: EatenFoodsViewModel = viewModel(factory = ViewModelInitializer.Factory))
{
    val foodUiState by viewModel.foodUiState.collectAsState()

    val eatenFoods = foodUiState.foods

    EatenFoodBody(viewModel ,foods = eatenFoods)

}

@Composable
fun EatenFoodBody(
    viewModel: EatenFoodsViewModel,
    foods: List<Food>,
    modifier: Modifier = Modifier
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if(foods.isEmpty()) {
            Text(
                textAlign = TextAlign.Center,
                text = "No food added yet today",
                fontSize = 20.sp
            )
        } else {
            EatenFoodList(
                viewModel,
                foods = foods,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }

}

@Composable
fun EatenFoodList(
    viewModel: EatenFoodsViewModel,
    foods: List<Food>,
    modifier: Modifier = Modifier
) {
    LazyColumn (modifier = modifier)
    {
        items(items = foods, key = { food -> food.id }) { food ->
            EatenFoodItem(
                viewModel,
                modifier = Modifier.padding(8.dp),
                food = food
            )
        }
    }
}

@Composable
fun EatenFoodItem(
    viewModel: EatenFoodsViewModel,
    modifier: Modifier = Modifier,
    food: Food) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {

        Column (modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        )
        {
            Text(
                text = "Name: ${food.name}",
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.headlineMedium
            )
            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween){
                Column (horizontalAlignment = Alignment.CenterHorizontally) {
                    InfoForFood("Calories", food.calories.toString())
                    InfoForFood("Carbs", food.carbs.toString())
                }
                Column (horizontalAlignment = Alignment.CenterHorizontally) {
                    InfoForFood("Protein", food.protein.toString())
                    InfoForFood("Fat", food.fat.toString())
                }
                Column (horizontalAlignment = Alignment.CenterHorizontally) {
                    InfoForFood("Sugar", food.sugar.toString())
                    IconButton(onClick = { viewModel.deleteFood(food) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            }
        }
    }
}

@Composable
fun InfoForFood(name: String, value: String) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = name,
            fontSize = 16.sp,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = value,
            fontSize = 16.sp,
            style = MaterialTheme.typography.bodyMedium
        )
    }

}
