package com.example.calorietracker_vamz.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calorietracker_vamz.R
import com.example.calorietracker_vamz.ViewModelInitializer
import com.example.calorietracker_vamz.navigation.NavigationDestination


object AddFoodDestination : NavigationDestination {
    override val path = "add_food"
    override val titleRes = R.string.addfoodtitle
    const val foodIdKey = "foodId"
    val pathWithArgs = "$path/{$foodIdKey}"
}
@Composable
fun AddFoodScreen(navigateBack: () -> Unit, viewModel: AddFoodScreenViewModel = viewModel(factory = ViewModelInitializer.Factory))
{


    val foodUiState by viewModel.foodUiState.collectAsState()
    val quantity by viewModel.quantity.collectAsState()

    Column (modifier = Modifier.padding(16.dp)) {
        Text(text = "Adding food", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(8.dp))

        if (foodUiState?.food != null) {
            Card {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row {
                        Text(
                            text = "Name: ${foodUiState!!.food!!.name}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Calories: ${foodUiState!!.food!!.calories}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Protein: ${foodUiState!!.food!!.protein}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Row {
                        Text(
                            text = "Carbs: ${foodUiState!!.food!!.carbs}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Fat: ${foodUiState!!.food!!.fat}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Sugar: ${foodUiState!!.food!!.sugar}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        } else {
            Text(text = "No food selected", style = MaterialTheme.typography.displayMedium)
        }


        Text(text = "Quantity", style = MaterialTheme.typography.bodyMedium)
        TextField(
            value = quantity.toString(),
            onValueChange = {
                newQuantity -> viewModel.quantity.value = newQuantity.toIntOrNull() ?: 0
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.addEatenFood()
            navigateBack()
        }) {
            Text(text = "Add food")
        }
    }
}
