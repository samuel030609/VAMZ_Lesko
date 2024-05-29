package com.example.calorietracker_vamz.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calorietracker_vamz.data.Food

@Composable
fun EatenFoodScreen(viewModel: EatenFoodsViewModel = viewModel())
{
    val eatenFoods by viewModel.eatenFoodsState.collectAsState()

    EatenFoodList(foods = eatenFoods)

}

@Composable
fun EatenFoodList(
    foods: List<Food>,
    modifier: Modifier = Modifier
) {
    LazyColumn (modifier = modifier)
    {
        items(items = foods, key = { food -> food.id }) { food ->
            EatenFoodItem(
                modifier = Modifier.padding(8.dp),
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

@Composable
fun EatenFoodItem(modifier: Modifier = Modifier, id: String = "", name: String = "", calories: String = "", protein: String = "", carbs: String = "", fat: String = "", sugar: String = "") {
    Card(
        modifier = modifier,
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