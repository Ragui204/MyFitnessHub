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
import androidx.lifecycle.viewModelScope
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myfitnesshub.data.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

// Add this data class above your ViewModel class



data class WorkoutSet(
    val weight: Double,
    val reps: Int
)

data class Exercise(
    val name: String,
    val sets: List<WorkoutSet>
)
@Entity(tableName = "workout_plans")
data class WorkoutPlan(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
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
    private val workoutDao = AppDatabase.getDatabase(application).workoutDao()
    val plans: StateFlow<List<WorkoutPlan>> = workoutDao.getAllPlans()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    var allExercises by mutableStateOf<List<ExerciseCategory>>(emptyList())
        private set


    fun saveNewPlan(title: String, exercises: List<Exercise>) {
        viewModelScope.launch(Dispatchers.IO) {
            val dao = AppDatabase.getDatabase(getApplication()).workoutDao()
            dao.insertPlan(WorkoutPlan(title = title, exercises = exercises))
        }
    }

    fun deletePlan(plan: WorkoutPlan) {
        viewModelScope.launch(Dispatchers.IO) {
            workoutDao.deletePlan(plan)
        }
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

