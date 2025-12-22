package com.example.myfitnesshub.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_sessions")
data class WorkoutSession(
    @PrimaryKey(autoGenerate = true) val sessionId: Int = 0,
    val userId: Int,
    val date: Long,
    val durationMinutes: Int,
    val caloriesBurned: Int
)