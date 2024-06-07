package com.example.calorietracker_vamz.ui.screens

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class StartViewModel : ViewModel() {

    val wasInfoFilled = MutableStateFlow(false)
}