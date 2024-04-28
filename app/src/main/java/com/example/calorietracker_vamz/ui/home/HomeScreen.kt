package com.example.calorietracker_vamz.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calorietracker_vamz.CalorieTrackerTopAppBar
import com.example.calorietracker_vamz.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier : Modifier = Modifier
) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CalorieTrackerTopAppBar(
                title = stringResource(R.string.hometopbar)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ },
                content = {
                    Icon(Icons.Filled.Add, contentDescription = "Add")
                }
            )
        },
    ) { innerPadding ->
        HomeBody(
            modifier = modifier.fillMaxSize(),
            contentPadding = innerPadding

        )

    }

}

@Composable
fun HomeBody(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ){
        CalorieIntake()
        NutrientsIntake()
    }
}

@Composable
fun CalorieIntake() {
    Column {
        Row {
            Text(
                text = "Calorie Intake",
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center
            )
        }
        Row {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Calories today: ",
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = "0",
                    modifier = Modifier.padding(16.dp)
                )
            }


            Column {
                Text(
                    text = "Calories needed: ",
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = "0",
                    modifier = Modifier.padding(16.dp)
                )
            }

        }

    }
}

@Composable
fun NutrientsIntake() {
    Column {
        Row {
            Text(
                text = "Nutrients Intake",
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center
            )
        }
        Row {
            Box {
                Column {
                    Text(
                        text = "Protein: ",
                        modifier = Modifier.padding(16.dp)
                    )
                    Text(
                        text = "0",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
            Column {
                Text(
                    text = "Carbs: ",
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = "0",
                    modifier = Modifier.padding(16.dp)
                )
            }
            Column {
                Text(
                    text = "Fat: ",
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = "0",
                    modifier = Modifier.padding(16.dp)
                )
            }
            Column {
                Text(
                    text = "Sugar: ",
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = "0",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

