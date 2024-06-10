package com.example.calorietracker_vamz.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calorietracker_vamz.InfoWasFilled
import com.example.calorietracker_vamz.R
import com.example.calorietracker_vamz.ViewModelInitializer

//Obrazovka, ktorá zobrazuje informácie používateľov
//teda kolko kalóriíí zjedol a ďalsie nutričné hodnoty
@Composable
fun StatisticScreen(
    statisticScreenViewModel: StatisticScreenViewModel = viewModel(factory = ViewModelInitializer.Factory)

) {
    val uiState by statisticScreenViewModel.uiState.collectAsState()
    val infoWasFilled = InfoWasFilled(context = LocalContext.current)
    val neededCalories = infoWasFilled.getCaloriesNeeded()
    statisticScreenViewModel.newCaloriesNeeded(neededCalories)
    LazyColumn (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(4.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ){
        item {
            CalorieSection(caloriesConsumed = uiState.eatenCalories, caloriesNeeded = uiState.neededCalories)
        }
        item {
            NutrientsSection(
                protein = uiState.eatenProtein,
                carbs = uiState.eatenCarbs,
                fat = uiState.eatenFat,
                sugar = uiState.eatenSugar
            )
        }

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
            .border(3.dp, Color.Blue, RoundedCornerShape(4.dp)),
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
                    text = stringResource(R.string.calories),
                    fontSize = 25.sp,
                    fontWeight = W600,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Column(
                    modifier = Modifier.weight(1f)
                        .padding(6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.consumed),
                        fontSize = 20.sp,
                        fontWeight = W500,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = String.format("%.2f", caloriesConsumed),
                        fontSize = 20.sp,
                        fontWeight = W500,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Column(
                    modifier = Modifier.weight(1f)
                        .padding(6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.needed),
                        fontSize = 20.sp,
                        fontWeight = W500,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = String.format("%.2f", caloriesNeeded),
                        fontSize = 20.sp,
                        fontWeight = W500,
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
            .border(3.dp, Color.Blue, RoundedCornerShape(4.dp)),
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
                    text = stringResource(R.string.nutrients),
                    fontSize = 25.sp,
                    fontWeight = W600,
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
                    NutrientItem(name = stringResource(R.string.protein), amount = String.format("%.2f", protein))
                    NutrientItem(name = stringResource(R.string.carbs), amount = String.format("%.2f", carbs))
                }
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    NutrientItem(name = stringResource(R.string.fat), amount = String.format("%.2f", fat))
                    NutrientItem(name = stringResource(R.string.sugar), amount = String.format("%.2f", sugar))
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
            fontSize = 20.sp,
            fontWeight = W500,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "$amount g",
            fontSize = 20.sp,
            fontWeight = W500,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

