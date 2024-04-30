package com.example.calorietracker_vamz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.calorietracker_vamz.ui.screens.HomeScreen
import com.example.calorietracker_vamz.ui.theme.CalorieTracker_VAMZTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalorieTracker_VAMZTheme {
                // A surface container using the 'background' color from the theme
                HomeScreen()
            }
        }
    }
}
