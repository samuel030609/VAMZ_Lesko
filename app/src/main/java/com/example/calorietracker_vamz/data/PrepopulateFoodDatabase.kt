package com.example.calorietracker_vamz.data

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.calorietracker_vamz.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray

class PrepopulateFoodDatabase (private val context: Context) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        
        CoroutineScope(Dispatchers.IO).launch {
            prePopulateDatabase(context)
        }
    }
}

suspend fun prePopulateDatabase(context: Context) {
    try {
        val foodDao = FoodDatabase.getDatabase(context).foodDao()

        val foodList: JSONArray =
            context.resources.openRawResource(R.raw.food).bufferedReader().use {
                it.readText()
            }.let {
                JSONArray(it)
            }

        foodList.takeIf { it.length() > 0 }?.let {
                list ->
            for (i in 0 until list.length()) {
                val food = list.getJSONObject(i)
                foodDao.insert(
                    Food(
                        food.getInt("id"),
                        food.getString("name"),
                        food.getInt("calories"),
                        food.getInt("protein"),
                        food.getInt("carbs"),
                        food.getInt("fat"),
                        food.getInt("sugar")
                    )
                )
            }
            Log.e("PrepopulateFoodDatabase", "Database prepopulated")
        }
    } catch (e: Exception) {
        Log.e("PrepopulateFoodDatabase", "Error prepopulating database: ${e.message}")
    }

}