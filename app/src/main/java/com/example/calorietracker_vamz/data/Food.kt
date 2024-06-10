package com.example.calorietracker_vamz.data

import androidx.room.Entity
import androidx.room.PrimaryKey

//Prvok databázy
@Entity(tableName = "food_table")
data class Food (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val calories: Double,
    val protein: Double,
    val carbs: Double,
    val fat: Double,
    val sugar: Double,
    val picture: String
)