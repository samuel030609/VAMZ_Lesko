package com.example.calorietracker_vamz.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calorietracker_vamz.data.Food
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EatenFoodsViewModel : ViewModel() {

    private val _eatenFoodsState = MutableStateFlow<List<Food>>(emptyList())
    val eatenFoodsState: StateFlow<List<Food>> = _eatenFoodsState

    fun addEatenFood(food: Food) {
        viewModelScope.launch {
            _eatenFoodsState.value += food
        }
    }
}