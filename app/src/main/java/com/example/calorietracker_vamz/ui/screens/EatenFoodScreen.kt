package com.example.calorietracker_vamz.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.W600
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
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),
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
            .padding(4.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {

        Column (modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text(
                text = food.name,
                fontSize = 25.sp,
                fontWeight = W600,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.headlineMedium
            )
            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically) {
                InfoForFood("Calories", food.calories.toString())
                InfoForFood("Carbs", food.carbs.toString())
                InfoForFood("Protein", food.protein.toString())

            }
            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically) {
                InfoForFood("Fat", food.fat.toString())
                InfoForFood("Sugar", food.sugar.toString())
                MyIconForFood(viewModel, food)
            }
        }
    }
}

@Composable
fun InfoForFood(name: String, value: String) {
    Card (
        modifier = Modifier
            .padding(8.dp)
            .size(70.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Blue, contentColor = Color.White)

    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
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
}

@Composable
fun MyIconForFood(
    viewModel: EatenFoodsViewModel,
    food: Food
) {
    Card (
        modifier = Modifier
            .padding(8.dp)
            .size(70.dp)
    ) {
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = { viewModel.deleteFood(food) }) {
                Icon(
                    Icons.Default.Delete, contentDescription = "Delete",
                    tint = Color.Red, modifier = Modifier.size(30.dp))
            }
        }

    }

}
