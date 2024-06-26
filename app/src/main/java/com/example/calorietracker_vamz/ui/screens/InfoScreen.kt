package com.example.calorietracker_vamz.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calorietracker_vamz.InfoWasFilled
import com.example.calorietracker_vamz.R
import com.example.calorietracker_vamz.ViewModelInitializer

//Obrazovka, ktorá sa zobrazí iba raz pri spustení aplikácie a používateľ musí zadať svoje údaje
@Composable
fun InfoScreen(navigateToStatisticScreen: () -> Unit ,viewModel: InfoScreenViewModel = viewModel(factory = ViewModelInitializer.Factory)) {
    val uiState by viewModel.uiState.collectAsState()
    val infoWasFilled = InfoWasFilled(context = LocalContext.current)
    val showSnackbar = remember { mutableStateOf(false) }
    LazyColumn (modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly) {

        item {
            OutlinedTextField(value = uiState.age.toString(),
                onValueChange = {viewModel.newAge(it.toIntOrNull() ?: 0)},
                label = { Text(stringResource(R.string.age)) },
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
        }
        item {
            OutlinedTextField(value = uiState.weight.toString(),
                onValueChange = {viewModel.newWeight(it.toIntOrNull() ?: 0)},
                label = { Text(stringResource(R.string.weight)) },
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
        }
        item {
            OutlinedTextField(value = uiState.height.toString(),
                onValueChange = {viewModel.newHeight(it.toIntOrNull() ?: 0)},
                label = { Text(stringResource(R.string.height)) },
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
        }
        item {
            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround) {
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                    Text(text = stringResource(R.string.male))
                    RadioButton(
                        selected = uiState.gender == "Male",
                        onClick = { viewModel.newGender("Male") },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color.Red,
                            unselectedColor = Color.Blue
                        )
                    )
                }
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                    Text(text = stringResource(R.string.female))
                    RadioButton(
                        selected = uiState.gender == "Female",
                        onClick = { viewModel.newGender("Female") },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color.Red,
                            unselectedColor = Color.Blue
                        )
                    )
                }

            }
        }

        item {
            Button(
                onClick = {
                    if (viewModel.isInfoValid()) {
                        viewModel.calculateCalories()
                        val caloriesNeeded = viewModel.getNeededCalories()
                        infoWasFilled.setInfoWasFilled(true)
                        infoWasFilled.setCaloriesNeeded(caloriesNeeded)

                        navigateToStatisticScreen()
                    } else {
                        showSnackbar.value = true
                    }


                }
            ) {
                Text(text = stringResource(R.string.save))
            }
        }


        if (showSnackbar.value) {
            item {
                Snackbar(
                    action = {
                        TextButton(onClick = { showSnackbar.value = false }) {
                            Text(stringResource(R.string.close))
                        }
                    },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(stringResource(R.string.fillall))
                }
            }

        }

    }
}