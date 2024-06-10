package com.example.calorietracker_vamz.ui.screens

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calorietracker_vamz.data.Food
import com.example.calorietracker_vamz.data.FoodRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class AddFoodScreenViewModel(private val savedStateHandle: SavedStateHandle, private val foodRepository: FoodRepository, private val eatenFoodRepository: FoodRepository) : ViewModel() {





    val foodUiState = MutableStateFlow<FoodUiState?>(null)



    val quantity = MutableStateFlow(0)

    private val foodId: Int = checkNotNull(savedStateHandle[AddFoodDestination.foodIdKey])

    init {
        viewModelScope.launch {
            foodUiState.value = FoodUiState(foodRepository.getFood(foodId)
                .filterNotNull()
                .first())
        }
    }

    fun isQuantityValid(): Boolean {
        return quantity.value in 1..1000
    }
    
    fun addEatenFood() {
        viewModelScope.launch {
            val food = foodUiState.value?.food
            val quantity = quantity.value
            if (food != null && isQuantityValid()) {
                val newFood = food.copy(
                    id = System.currentTimeMillis().toInt(),
                    calories = String.format("%.2f", food.calories / 100 * quantity).replace(",", ".").toDouble(),
                    protein = String.format("%.2f", food.protein / 100 * quantity).replace(",", ".").toDouble(),
                    carbs = String.format("%.2f", food.carbs / 100 * quantity).replace(",", ".").toDouble(),
                    fat = String.format("%.2f", food.fat / 100 * quantity).replace(",", ".").toDouble(),
                    sugar = String.format("%.2f", food.sugar / 100 * quantity).replace(",", ".").toDouble()
                )
                eatenFoodRepository.insert(newFood)
            }
        }
    }
}

data class FoodUiState(
    val food: Food? = null
)