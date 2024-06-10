package com.example.calorietracker_vamz.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calorietracker_vamz.data.Food
import com.example.calorietracker_vamz.data.FoodRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

//ViewModel pre obrazovku FoodScreen, ktorý si udržiava v stave zoznam všetkých jedál
class FoodScreenViewModel (foodRepository: FoodRepository) : ViewModel() {



    val foodUiState: StateFlow<FoodsUiState> =
        foodRepository.getAlphabetizedFoods().map { FoodsUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = FoodsUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

}

data class FoodsUiState(
    val foods: List<Food> = listOf()
)