package com.example.calorietracker_vamz

import android.app.Application
import com.example.calorietracker_vamz.data.AppContainer
import com.example.calorietracker_vamz.data.AppDataContainer

class InventoryApplication : Application(){

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}