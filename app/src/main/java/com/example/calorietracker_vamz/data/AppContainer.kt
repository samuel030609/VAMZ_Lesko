package com.example.calorietracker_vamz.data

import android.content.Context

interface AppContainer2 {
    val eatenFoodRepository: FoodRepository
}
interface AppContainer {
    val foodRepository: FoodRepository
}
class AppDataContainer(private val context: Context) : AppContainer {
    override val foodRepository: FoodRepository by lazy {
        OfflineFoodRepository(FoodDatabase.getDatabase(context).foodDao())
    }
}

class AppDataContainer2(private val context: Context) : AppContainer2 {
    override val eatenFoodRepository: FoodRepository by lazy {
        OfflineFoodRepository(EatenFoodDatabase.getDatabase(context).foodDao())
    }
}