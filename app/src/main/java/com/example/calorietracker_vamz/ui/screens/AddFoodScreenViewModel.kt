package com.example.calorietracker_vamz.ui.screens

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calorietracker_vamz.data.Food
import com.example.calorietracker_vamz.data.FoodRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class AddFoodScreenViewModel(private val savedStateHandle: SavedStateHandle, private val foodRepository: FoodRepository, private val eatenFoodsViewModel: EatenFoodsViewModel) : ViewModel() {



    private val _food = MutableStateFlow<Food?>(null)
    val food = _food



    val quantity = MutableStateFlow(0)


    init {
        val savedFood = savedStateHandle.get<Food>("food")
        if (savedFood != null) {
            _food.value = savedFood
        }
    }

    fun selectFood(foodId: Int) {
        viewModelScope.launch {
            val food = foodRepository.getFood(foodId).first()
            if (food != null) {
                _food.value = food
                savedStateHandle["food"] = food
            }
        }
    }

    fun addEatenFood() {
        viewModelScope.launch {
            val food = food.value
            val quantity = quantity.value
            if (food != null && quantity > 0) {
                val newFood = food.copy(
                    calories = food.calories / 100 * quantity,
                    protein = food.protein / 100 * quantity,
                    carbs = food.carbs / 100 * quantity,
                    fat = food.fat / 100 * quantity,
                    sugar = food.sugar / 100 * quantity
                )
                eatenFoodsViewModel.addEatenFood(newFood)
            }
        }
    }
}