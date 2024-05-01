package com.example.calorietracker_vamz.data

import kotlinx.coroutines.flow.Flow

interface FoodRepository {
    fun getAlphabetizedFoods(): Flow<List<Food>>
    fun searchDatabase(searchQuery: String): Flow<List<Food>?>
    suspend fun insert(food: Food)
    suspend fun update(food: Food)
    suspend fun delete(food: Food)
    fun getFood(id: Int): Flow<Food?>
}