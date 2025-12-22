package com.example.myfitnesshub.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_metrics")
data class UserMetric(
    @PrimaryKey (autoGenerate = true) val metricId: Int = 0,
    val userId: Int, //Key Link to User
    val date: Long,
    val weightKG: Float,
    val bodyFatPercentage: Float
)