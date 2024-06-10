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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calorietracker_vamz.R
import com.example.calorietracker_vamz.ViewModelInitializer
import com.example.calorietracker_vamz.data.Food

//Obrazovka, ktorá zobrazuje zjedené jedlá s ich informáciami prepočítanými na konkrétne zjedené množstvo
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
    foods: List<Food>
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
                text = stringResource(R.string.foodadded),
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
                InfoForFood(stringResource(R.string.calories2), String.format("%.2f", food.calories))
                InfoForFood(stringResource(R.string.carbs), String.format("%.2f", food.carbs))
                InfoForFood(stringResource(R.string.protein), String.format("%.2f", food.protein))

            }
            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically) {
                InfoForFood(stringResource(R.string.fat), String.format("%.2f", food.fat))
                InfoForFood(stringResource(R.string.sugar), String.format("%.2f", food.sugar))
                MyIconForFood(viewModel, food)
            }
        }
    }
}

@Composable
fun InfoForFood(name: String, value: String) {
    Card (
        modifier = Modifier
            .padding(7.dp)
            .size(75.dp),
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
                fontSize = 15.sp,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = value,
                fontSize = 15.sp,
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
            .padding(7.dp)
            .size(75.dp)
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
