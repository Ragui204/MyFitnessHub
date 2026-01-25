package com.example.myfitnesshub.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_sets")
data class ExerciseSet(
    @PrimaryKey(autoGenerate = true) val setId: Int = 0, // Changed from userId
    val sessionId: Int,
    val exerciseName: String,
    val weightKG: Float,
    val reps: Int,
    val rir: Int
)