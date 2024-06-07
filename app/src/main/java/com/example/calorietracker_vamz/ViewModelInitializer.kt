package com.example.calorietracker_vamz

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.calorietracker_vamz.ui.screens.AddFoodScreenViewModel
import com.example.calorietracker_vamz.ui.screens.EatenFoodsViewModel
import com.example.calorietracker_vamz.ui.screens.FoodScreenViewModel
import com.example.calorietracker_vamz.ui.screens.InfoScreenViewModel
import com.example.calorietracker_vamz.ui.screens.StatisticScreenViewModel

object ViewModelInitializer {
    val Factory = viewModelFactory {

        initializer {
            FoodScreenViewModel(
                inventoryApplication().container.foodRepository

            )
        }

        initializer {
            EatenFoodsViewModel(
                inventoryApplication().container2.eatenFoodRepository
            )
        }

        initializer {
            InfoScreenViewModel()
        }

        initializer {
            StatisticScreenViewModel(
                inventoryApplication().container2.eatenFoodRepository
            )
        }

        initializer {
            AddFoodScreenViewModel(
                this.createSavedStateHandle(),
                inventoryApplication().container.foodRepository,
                inventoryApplication().container2.eatenFoodRepository
            )
        }

    }
}

fun CreationExtras.inventoryApplication(): InventoryApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as InventoryApplication)