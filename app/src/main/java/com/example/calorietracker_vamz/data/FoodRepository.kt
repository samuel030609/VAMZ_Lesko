package com.example.calorietracker_vamz.data

import kotlinx.coroutines.flow.Flow

//Interface na deklaráciu metód pre databázu
interface FoodRepository {
    fun getAlphabetizedFoods(): Flow<List<Food>>
    suspend fun insert(food: Food)
    suspend fun update(food: Food)
    suspend fun delete(food: Food)
    fun getFood(id: Int): Flow<Food?>

    suspend fun deleteAll()

}