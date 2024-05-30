package com.example.calorietracker_vamz.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Food::class], version = 1, exportSchema = false)
abstract class EatenFoodDatabase : RoomDatabase() {

    abstract fun foodDao(): FoodDao

    companion object {
        @Volatile
        private var Instance: EatenFoodDatabase? = null

        fun getDatabase(context: Context): EatenFoodDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, EatenFoodDatabase::class.java, "eaten_food_database")
                    .build()
                    .also {
                        Instance = it
                    }
            }
        }
    }

}