package com.example.calorietracker_vamz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.calorietracker_vamz.ui.theme.CalorieTracker_VAMZTheme
import java.util.Calendar
import java.util.concurrent.TimeUnit

//Hlavná aktivita, ktorá potom spúšta HomeScreen
//a nastavuje sa tu taktiež vykonanie práce pre workera
//aby sa vykonávala o polnoci a každých 24 hodín
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val current = Calendar.getInstance()
        val midnight = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            add(Calendar.DAY_OF_MONTH, 1)
        }
        val initialDelay = midnight.timeInMillis - current.timeInMillis

        val workRequest = PeriodicWorkRequestBuilder<EatensFoodWorker>(24, TimeUnit.HOURS)
            .setInitialDelay(initialDelay, TimeUnit.MILLISECONDS)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "EatensFoodWorker",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
        setContent {
            CalorieTracker_VAMZTheme {
                // A surface container using the 'background' color from the theme
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    HomeScreen()
                }
            }
        }
    }
}
