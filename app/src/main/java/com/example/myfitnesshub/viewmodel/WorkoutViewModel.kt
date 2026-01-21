package com.example.myfitnesshub.viewmodel

import android.view.View
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// Add this data class above your ViewModel class
data class WorkoutSet(
    val weight: Double,
    val reps: Int
)

data class Exercise(
    val name: String,
    val sets: List<WorkoutSet>
)

data class WorkoutPlan(
    val title: String,
    val exercises: List<Exercise> = emptyList()
)



class WorkoutViewModel : ViewModel() {
    private val _selectedTabIndex = MutableStateFlow(0)
    val selectedTabIndex: StateFlow<Int> = _selectedTabIndex
    val tabs = listOf("Routine", "Exercises", "Stats")

    // ADD THIS: A list to hold your self-made plans
    val plans = listOf(
        WorkoutPlan(
            title = "Push Day",
            exercises = listOf(
                Exercise("Bench Press", listOf(WorkoutSet(80.0, 8), WorkoutSet(80.0, 8))),
                Exercise("Tricep Dips", listOf(WorkoutSet(0.0, 12)))
            )
        ),
        WorkoutPlan(
            title = "Legs",
            exercises = listOf(
                Exercise("Squats", listOf(WorkoutSet(100.0, 5)))
            )
        )
    )

    fun selectedTab(index: Int) {
        _selectedTabIndex.value = index
    }
}

