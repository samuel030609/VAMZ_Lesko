package com.example.calorietracker_vamz.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(food: Food)

    @Update
    suspend fun update(food: Food)

    @Delete
    suspend fun delete(food: Food)


    @Query("SELECT * FROM food_table WHERE id = :id")
    fun getFood(id: Int): Flow<Food>

    @Query("SELECT * FROM food_table ORDER BY name ASC")
    fun getAlphabetizedFoods(): Flow<List<Food>>

}