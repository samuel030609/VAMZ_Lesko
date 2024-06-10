package com.example.calorietracker_vamz

import android.content.Context
import android.content.SharedPreferences

//Trieda, kde sú uložené informácie o tom či už teda používateľ vyplnil prvú obrazovku a o potrebných kalóriach používateľa
//je to preto aby tieto informácie boli zachované aj po zavretí aplikáie
//keďže ostatné informácie sú udržiavané tým že sú z databázy netreba ich inak ukladať
class InfoWasFilled (context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPreference", Context.MODE_PRIVATE)

    fun setInfoWasFilled(infoWasFilled: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("infoWasFilled", infoWasFilled)
        editor.apply()
    }
    fun getInfoWasFilled(): Boolean {
        return sharedPreferences.getBoolean("infoWasFilled", false)
    }

    fun setCaloriesNeeded(caloriesNeeded: Double) {
        val editor = sharedPreferences.edit()
        editor.putFloat("caloriesNeeded", caloriesNeeded.toFloat())
        editor.apply()
    }

    fun getCaloriesNeeded(): Double {
        return sharedPreferences.getFloat("caloriesNeeded", 0.0f).toDouble()
    }
}