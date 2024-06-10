package com.example.calorietracker_vamz.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Trieda databázy pre  všetky jedlá
@Database(entities = [Food::class], version = 1, exportSchema = false)
abstract class FoodDatabase : RoomDatabase() {

        abstract fun foodDao(): FoodDao

        companion object {
            @Volatile
            private var Instance: FoodDatabase? = null

            fun getDatabase(context: Context): FoodDatabase {
                return Instance ?: synchronized(this) {
                    Room.databaseBuilder(context, FoodDatabase::class.java, "food_database")
                        .addCallback(PrepopulateFoodDatabase(context))
                        .build()
                        .also {
                        Instance = it
                    }
                }
            }
        }

}