package com.example.calorietracker_vamz.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    EatenFoodBody(foods = eatenFoods)

}

@Composable
fun EatenFoodBody(
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
                foods = foods,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }

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
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {

        Column (modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        )
        {
            Text(
                text = "Name: $name",
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.headlineMedium
            )
            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween){
                Column (horizontalAlignment = Alignment.CenterHorizontally) {
                    InfoForFood("ID" , id)
                    InfoForFood("Carbs", carbs)
                }
                Column (horizontalAlignment = Alignment.CenterHorizontally) {
                    InfoForFood("Calories", calories)
                    InfoForFood("Fat", fat)
                }
                Column (horizontalAlignment = Alignment.CenterHorizontally) {
                    InfoForFood("Protein", protein)
                    InfoForFood("Sugar", sugar)
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

@Composable
fun InfoCardForFood(name: String, value: String) {
    val backgroundColor = MaterialTheme.colorScheme.primary
    Card(
        modifier = Modifier
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor, contentColor = Color.White),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column (
            modifier = Modifier
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = name,
                fontSize = 16.sp,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = value,
                fontSize = 16.sp,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}