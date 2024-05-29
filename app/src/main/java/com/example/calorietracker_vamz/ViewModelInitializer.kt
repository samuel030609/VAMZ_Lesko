package com.example.calorietracker_vamz

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.calorietracker_vamz.ui.screens.AddFoodScreenViewModel
import com.example.calorietracker_vamz.ui.screens.EatenFoodsViewModel
import com.example.calorietracker_vamz.ui.screens.FoodScreenViewModel

object ViewModelInitializer {
    val Factory = viewModelFactory {

        initializer {
            FoodScreenViewModel(
                inventoryApplication().container.foodRepository

            )
        }

        initializer {
            val eatenFoodsViewModel = EatenFoodsViewModel()
            AddFoodScreenViewModel(
                this.createSavedStateHandle(),
                inventoryApplication().container.foodRepository,
                eatenFoodsViewModel
            )
        }

    }
}

fun CreationExtras.inventoryApplication(): InventoryApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as InventoryApplication)