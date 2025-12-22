package com.example.myfitnesshub.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sleep_records")
data class SleepRecord(
    @PrimaryKey(autoGenerate = true) val sleepId: Int = 0,
    val userId: Int,
    val startDate: Long,
    val endDate: Long,
    val deepSleepMinutes: Int,
    val remSleepMinutes: Int,
    val awakeMinutes: Int
)
