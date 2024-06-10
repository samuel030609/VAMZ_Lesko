package com.example.calorietracker_vamz.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calorietracker_vamz.R
import com.example.calorietracker_vamz.ViewModelInitializer
import com.example.calorietracker_vamz.navigation.NavigationDestination


//Destinácia pre poslednú obrazovku, aby sme mohli na ňu prejsť s konkrétnym jedlom
object AddFoodDestination : NavigationDestination {
    override val path = "add_food"
    const val foodIdKey = "foodId"
    val pathWithArgs = "$path/{$foodIdKey}"
}

//Obrazovka, pre pridanie jedla do zjedených dnešných jedál
@Composable
fun AddFoodScreen(navigateBack: () -> Unit, viewModel: AddFoodScreenViewModel = viewModel(factory = ViewModelInitializer.Factory))
{


    val foodUiState by viewModel.foodUiState.collectAsState()
    val food = foodUiState?.food
    val quantity by viewModel.quantity.collectAsState()
    val isSnackbar = remember { mutableStateOf(false) }

    LazyColumn (modifier = Modifier
        .fillMaxSize()
        .padding(16.dp), verticalArrangement = Arrangement.SpaceEvenly) {

        if (food != null) {
            item {
                Card {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            fontSize = 25.sp,
                            text = food.name,
                            fontWeight = W600,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            fontSize = 25.sp,
                            fontWeight = W600,
                            text = "${stringResource(R.string.calories)} ${food.calories}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Row (modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(
                                fontSize = 20.sp,
                                text = "${stringResource(R.string.protein)} ${food.protein}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                fontSize = 20.sp,
                                text = "${stringResource(R.string.carbs)} ${food.carbs}",
                                style = MaterialTheme.typography.bodyMedium
                            )

                        }

                        Row (modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(
                                fontSize = 20.sp,
                                text = "${stringResource(R.string.fat)} ${food.fat}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                fontSize = 20.sp,
                                text = "${stringResource(R.string.sugar)} ${food.sugar}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        } else {
            item {
                Text(text = stringResource(R.string.nofood), style = MaterialTheme.typography.displayMedium)
            }

        }

        item {
            OutlinedTextField(
                value = quantity.toString(),
                onValueChange = {
                        newQuantity -> viewModel.quantity.value = newQuantity.toIntOrNull() ?: 0
                },
                label = { Text(stringResource(R.string.quantity)) },
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
            Row (modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()) {
                Button( onClick = {
                    if (viewModel.isQuantityValid()) {
                        viewModel.addEatenFood()
                        navigateBack()
                    } else {
                        isSnackbar.value = true
                    }
                }) {
                    Text(text = stringResource(R.string.addfood))
                }
                Spacer(modifier = Modifier.weight(1f))
                Button(onClick = {
                    navigateBack()
                }) {
                    Text(text = stringResource(R.string.close))
                }
            }
        }

        if (isSnackbar.value) {
            item {
                Snackbar(
                    action = {
                        TextButton(onClick = { isSnackbar.value = false }) {
                            Text(stringResource(R.string.close))
                        }
                    },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(stringResource(R.string.quantitycontrol))
                }
            }

        }
    }
}
