package com.example.calorietracker_vamz

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.calorietracker_vamz.data.AppDataContainer2
import kotlinx.coroutines.coroutineScope

//Trieda worker, ktorá slúži na to aby sa na konci dňa vymazali všetky zjedené jedlá
class EatensFoodWorker(appContext: Context, params: WorkerParameters):
        CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result = coroutineScope {
        val eatenFoodRepository = AppDataContainer2(applicationContext).eatenFoodRepository
        eatenFoodRepository.deleteAll()
        Result.success()
    }
}