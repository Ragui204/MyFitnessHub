package com.example.myfitnesshub.data

import androidx.room.TypeConverter
import com.example.myfitnesshub.viewmodel.Exercise
import com.example.myfitnesshub.viewmodel.WorkoutDay
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    // 1. Converter for Exercises (used in old plans)
    @TypeConverter
    fun fromExerciseList(value: List<Exercise>): String = gson.toJson(value)

    @TypeConverter
    fun toExerciseList(value: String): List<Exercise> {
        val listType = object : TypeToken<List<Exercise>>() {}.type
        return gson.fromJson(value, listType) ?: emptyList()
    }

    // 2. Converter for WorkoutDays (REQUIRED for your new multi-day logic)
    @TypeConverter
    fun fromWorkoutDayList(value: List<WorkoutDay>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toWorkoutDayList(value: String): List<WorkoutDay> {
        val listType = object : TypeToken<List<WorkoutDay>>() {}.type
        return Gson().fromJson(value, listType) ?: emptyList()
    }
}
