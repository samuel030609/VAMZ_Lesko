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

    fun addEatenFood() {
        viewModelScope.launch {
            val food = foodUiState.value?.food
            val quantity = quantity.value
            if (food != null && quantity > 0) {
                val newFood = food.copy(
                    calories = food.calories / 100 * quantity,
                    protein = food.protein / 100 * quantity,
                    carbs = food.carbs / 100 * quantity,
                    fat = food.fat / 100 * quantity,
                    sugar = food.sugar / 100 * quantity
                )
                eatenFoodRepository.insert(newFood)
            }
        }
    }
}

data class FoodUiState(
    val food: Food? = null
)