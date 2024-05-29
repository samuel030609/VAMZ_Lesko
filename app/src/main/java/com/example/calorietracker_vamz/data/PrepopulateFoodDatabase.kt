package com.example.calorietracker_vamz.data

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.calorietracker_vamz.R
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONArray

class PrepopulateFoodDatabase (private val context: Context) : RoomDatabase.Callback() {

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        
        GlobalScope.launch {
            prePopulateDatabase(context)
        }
    }
}

suspend fun prePopulateDatabase(context: Context) {
    try {

        val foodDao = FoodDatabase.getDatabase(context).foodDao()

        val foodList: JSONArray =
            context.resources.openRawResource(R.raw.food).bufferedReader().use {
                JSONArray(it.readText())
            }

        foodList.takeIf { it.length() > 0 }?.let { list ->
            for (i in 0 until list.length()) {
                val food = list.getJSONObject(i)
                foodDao.insert(
                    Food(
                        name = food.getString("name"),
                        calories = food.getInt("calories"),
                        protein = food.getInt("protein"),
                        carbs = food.getInt("carbs"),
                        fat = food.getInt("fat"),
                        sugar = food.getInt("sugar")
                    )
                )
            }
            Log.e("PrepopulateFoodDatabase", "Database prepopulated")
        }
    } catch (e: Exception) {
        Log.e("PrepopulateFoodDatabase", "Error prepopulating database: ${e.message}")
    }

}