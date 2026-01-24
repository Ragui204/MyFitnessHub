package com.example.myfitnesshub.viewmodel

import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import android.app.Application
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import androidx.lifecycle.AndroidViewModel

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

data class ExerciseCategory(
    val category: String,
    val exercises: List<String>
)



class WorkoutViewModel(application: Application) : AndroidViewModel(application) {
    private val _selectedTabIndex = MutableStateFlow(0)
    val selectedTabIndex: StateFlow<Int> = _selectedTabIndex
    val tabs = listOf("Routine", "Exercises", "Stats")

    //list to hold your self-made plans
    private val _plans = MutableStateFlow(listOf(
        WorkoutPlan("Push Day", listOf(Exercise("Bench Press", listOf(WorkoutSet(80.0, 8)))))
    ))
    val plans: StateFlow<List<WorkoutPlan>> = _plans
    var allExercises by mutableStateOf<List<ExerciseCategory>>(emptyList())
        private set

    fun addNewPlan(title: String, selectedExercises: List<Exercise>) {
        val newPlan = WorkoutPlan(title, exercises = selectedExercises)
        _plans.value = _plans.value + newPlan
    }

    init {
        loadExercises()
    }

    private fun loadExercises() {
        try {
            // 1. Read the file from assets
            val jsonString = getApplication<Application>().assets
                .open("exercises.json")
                .bufferedReader()
                .use { it.readText() }

            // 2. Parse JSON string into List<ExerciseCategory>
            val listType = object : TypeToken<List<ExerciseCategory>>() {}.type
            allExercises = Gson().fromJson(jsonString, listType)
        } catch (e: Exception) {
            e.printStackTrace()
            allExercises = emptyList()
        }
    }

    fun selectedTab(index: Int) {
        _selectedTabIndex.value = index
    }
}

