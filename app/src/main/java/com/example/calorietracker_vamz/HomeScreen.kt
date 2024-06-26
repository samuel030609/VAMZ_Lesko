package com.example.calorietracker_vamz

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.calorietracker_vamz.navigation.MyBottomBar
import com.example.calorietracker_vamz.navigation.MyNavHost
import com.example.calorietracker_vamz.navigation.Screens

//Composable, ktoré sa vytvára pri spustení aplikácie
//služí na definovanie celého layoutu aplikácie
//obsahuje hornú lištu, spodnú lištu, ktorá sa nezobrazuje na všetkých obrazovkách
//a obsahuje aj navigačný host, ktorý zobrazuje jednotlivé obrazovky v strede medzi hornou a spodnou lištou
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    val homeNavController = rememberNavController()

    val infoWasFilled = InfoWasFilled(context = LocalContext.current)
    val wasInfoFilled = infoWasFilled.getInfoWasFilled()

    val startDestination = if (wasInfoFilled) {
        Screens.StatisticScreen.path
    } else {
        Screens.InfoScreen.path
    }

    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Calorie Tracker", modifier = Modifier.padding(16.dp), color = Color.White)
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue)
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
                navController = homeNavController,
                startDestination = startDestination
            )
        }

    }
}