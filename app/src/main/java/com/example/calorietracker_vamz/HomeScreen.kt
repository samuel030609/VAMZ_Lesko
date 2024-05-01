package com.example.calorietracker_vamz

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.calorietracker_vamz.navigation.MyBottomBar
import com.example.calorietracker_vamz.navigation.MyNavHost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    val homeNavController = rememberNavController()

    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Calorie Tracker", modifier = Modifier.padding(16.dp))
                }
            )
        },
        bottomBar = {
            MyBottomBar(navController = homeNavController)
        }
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(it)

        ){
            MyNavHost(
                navController = homeNavController
            )
        }

    }
}