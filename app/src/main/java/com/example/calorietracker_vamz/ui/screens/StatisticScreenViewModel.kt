package com.example.calorietracker_vamz.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calorietracker_vamz.data.Food
import com.example.calorietracker_vamz.data.FoodRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StatisticScreenViewModel (
    private val eatenFoodRepository: FoodRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(StatisticUiState())
    val uiState: StateFlow<StatisticUiState> = _uiState



    init {
        findFood()
    }

    fun newCaloriesNeeded(neededCalories: Double) {
        _uiState.value = _uiState.value.copy(neededCalories = neededCalories)
    }
    private fun findFood() = viewModelScope.launch {
        eatenFoodRepository.getAlphabetizedFoods().collect { foods ->
            updateUiState(foods)
        }
    }
    private fun updateUiState(foods: List<Food>) {
        val eatenCalories = foods.sumOf { it.calories }
        val eatenProtein = foods.sumOf { it.protein }
        val eatenCarbs = foods.sumOf { it.carbs }
        val eatenFat = foods.sumOf { it.fat }
        val eatenSugar = foods.sumOf { it.sugar }
        val neededCalories = _uiState.value.neededCalories

        _uiState.value = StatisticUiState(
            eatenCalories = eatenCalories,
            eatenProtein = eatenProtein,
            eatenCarbs = eatenCarbs,
            eatenFat = eatenFat,
            eatenSugar = eatenSugar,
            neededCalories = neededCalories
        )
    }


}

data class StatisticUiState(
    val eatenCalories: Double = 0.0,
    val eatenProtein: Double = 0.0,
    val eatenCarbs: Double = 0.0,
    val eatenFat: Double = 0.0,
    val eatenSugar: Double = 0.0,
    val neededCalories: Double = 0.0
)
