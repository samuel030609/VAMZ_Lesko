package com.example.calorietracker_vamz.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calorietracker_vamz.data.Food
import com.example.calorietracker_vamz.data.FoodRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class EatenFoodsViewModel (eatenFoodRepository: FoodRepository): ViewModel() {

    val foodUiState: StateFlow<EatenFoodUiState> =
        eatenFoodRepository.getAlphabetizedFoods().map { EatenFoodUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = EatenFoodUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class EatenFoodUiState(
    val foods: List<Food> = listOf()
)