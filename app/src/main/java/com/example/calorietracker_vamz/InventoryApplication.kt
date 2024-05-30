package com.example.calorietracker_vamz

import android.app.Application
import com.example.calorietracker_vamz.data.AppContainer
import com.example.calorietracker_vamz.data.AppContainer2
import com.example.calorietracker_vamz.data.AppDataContainer
import com.example.calorietracker_vamz.data.AppDataContainer2

class InventoryApplication : Application(){

    lateinit var container: AppContainer
    lateinit var container2: AppContainer2
    override fun onCreate() {
        super.onCreate()
        container2 = AppDataContainer2(this)
        container = AppDataContainer(this)
    }
}