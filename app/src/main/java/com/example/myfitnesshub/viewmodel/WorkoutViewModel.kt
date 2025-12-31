package com.example.myfitnesshub.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WorkoutViewModel : ViewModel() {
    private val _selectedTabIndex = MutableStateFlow(0)
    val selectedTabIndex: StateFlow<Int> = _selectedTabIndex

    val tabs = listOf("Routine", "Exercises", "Stats")

    fun selectedTab(index: Int) {
        _selectedTabIndex.value = index
    }

}