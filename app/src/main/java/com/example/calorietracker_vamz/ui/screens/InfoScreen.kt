package com.example.calorietracker_vamz.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calorietracker_vamz.ViewModelInitializer

@Composable
fun InfoScreen(startViewModel: StartViewModel,navigateToStatisticScreen: () -> Unit ,viewModel: InfoScreenViewModel = viewModel(factory = ViewModelInitializer.Factory),
    statisticScreenViewModel: StatisticScreenViewModel = viewModel(factory = ViewModelInitializer.Factory)) {
    val uiState by viewModel.uiState.collectAsState()

    Column (modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween) {
        OutlinedTextField(value = uiState.age.toString(),
            onValueChange = {viewModel.newAge(it.toInt())},
            label = { Text("Age") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(value = uiState.weight.toString(),
            onValueChange = {viewModel.newWeight(it.toInt())},
            label = { Text("Weight") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(value = uiState.height.toString(),
            onValueChange = {viewModel.newHeight(it.toInt())},
            label = { Text("Height") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            RadioButton(
                selected = uiState.gender == "Male",
                onClick = { viewModel.newGender("Male") }
            )
            Text(text = "Male")
            RadioButton(
                selected = uiState.gender == "Female",
                onClick = { viewModel.newGender("Female") }
            )
            Text(text = "Female")
        }

        Button(
            onClick = {
                viewModel.calculateCalories()
                val caloriesNeeded = viewModel.getNeededCalories()
                statisticScreenViewModel.newCaloriesNeeded(caloriesNeeded)
                startViewModel.wasInfoFilled.value = true
                navigateToStatisticScreen()
            }
        ) {
            Text(text = "Save")
        }

    }
}