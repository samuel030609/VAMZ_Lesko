package com.example.calorietracker_vamz.data

import kotlinx.coroutines.flow.Flow

class OfflineFoodRepository (private val foodDao: FoodDao) : FoodRepository {

        override fun getAlphabetizedFoods(): Flow<List<Food>> = foodDao.getAlphabetizedFoods()

        override fun searchDatabase(searchQuery: String): Flow<List<Food>?> = foodDao.searchDatabase(searchQuery)

        override suspend fun insert(food: Food) {
            foodDao.insert(food)
        }

        override suspend fun update(food: Food) {
            foodDao.update(food)
        }

        override suspend fun delete(food: Food) {
            foodDao.delete(food)
        }

        override fun getFood(id: Int): Flow<Food?> = foodDao.getFood(id)

}