package com.example.calorietracker_vamz.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calorietracker_vamz.InfoWasFilled
import com.example.calorietracker_vamz.ViewModelInitializer

@Composable
fun StatisticScreen(
    modifier: Modifier = Modifier,
    statisticScreenViewModel: StatisticScreenViewModel = viewModel(factory = ViewModelInitializer.Factory)

) {
    val uiState by statisticScreenViewModel.uiState.collectAsState()
    val infoWasFilled = InfoWasFilled(context = LocalContext.current)
    val neededCalories = infoWasFilled.getCaloriesNeeded()
    statisticScreenViewModel.newCaloriesNeeded(neededCalories)

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ){
        CalorieSection(caloriesConsumed = uiState.eatenCalories, caloriesNeeded = uiState.neededCalories)
        NutrientsSection(
            protein = uiState.eatenProtein,
            carbs = uiState.eatenCarbs,
            fat = uiState.eatenFat,
            sugar = uiState.eatenSugar
        )
    }
}

@Composable
fun CalorieSection(
    caloriesConsumed: Double,
    caloriesNeeded: Double
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(1.dp, Color.Blue, RoundedCornerShape(4.dp)),
        color = Color.White,
        shape = RoundedCornerShape(4.dp)
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            Row(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Calories",
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column(
                    modifier = Modifier.weight(1f)
                        .padding(6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Calories Consumed",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "$caloriesConsumed",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Column(
                    modifier = Modifier.weight(1f)
                        .padding(6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Calories Needed",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "$caloriesNeeded",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

@Composable
fun NutrientsSection(
    protein: Double,
    carbs: Double,
    fat: Double,
    sugar: Double
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(1.dp, Color.Blue, RoundedCornerShape(4.dp)),
        color = Color.White,
        shape = RoundedCornerShape(4.dp)
    )
    {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Nutrients",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    NutrientItem(name = "Protein", amount = "$protein g")
                    NutrientItem(name = "Carbohydrates", amount = "$carbs g")
                }
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    NutrientItem(name = "Fat", amount = "$fat g")
                    NutrientItem(name = "Fiber", amount = "$sugar g")
                }
            }
        }
    }
}

@Composable
fun NutrientItem(name: String, amount: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = amount,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

