package com.example.calorietracker_vamz.ui.screens

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class InfoScreenViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(InfoUiState())

    val uiState: StateFlow<InfoUiState> = _uiState

    fun newAge(age: Int) {
        _uiState.value = _uiState.value.copy(age = age)
    }

    fun newWeight(weight: Int) {
        _uiState.value = _uiState.value.copy(weight = weight)
    }

    fun newHeight(height: Int) {
        _uiState.value = _uiState.value.copy(height = height)
    }

    fun newGender(gender: String) {
        _uiState.value = _uiState.value.copy(gender = gender)
    }

    fun calculateCalories() {
        val age = _uiState.value.age
        val weight = _uiState.value.weight
        val height = _uiState.value.height
        val gender = _uiState.value.gender
        val calories =  if (gender == "Male") {
            66.5 + (13.75 * weight) + (5 * height) - (6.75 * age)
        } else {
            655 + (9.5 * weight) + (1.85 * height) - (4.7 * age)

        }
        _uiState.value = _uiState.value.copy(neededCalories = calories)
    }

    fun getNeededCalories(): Double {
        return _uiState.value.neededCalories
    }
}

data class InfoUiState(
    val age : Int = 0,
    val weight : Int = 0,
    val height : Int = 0,
    val gender : String = "",
    val neededCalories : Double = 0.0
)