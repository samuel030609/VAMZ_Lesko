package com.example.calorietracker_vamz

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.calorietracker_vamz.data.AppDataContainer2
import kotlinx.coroutines.coroutineScope

class EatensFoodWorker(appContext: Context, params: WorkerParameters):
        CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result = coroutineScope {
        val eatenFoodRepository = AppDataContainer2(applicationContext).eatenFoodRepository
        eatenFoodRepository.deleteAll()
        Result.success()
    }
}