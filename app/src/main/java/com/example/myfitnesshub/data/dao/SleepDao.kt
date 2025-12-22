package com.example.myfitnesshub.data.dao

import androidx.room.*
import com.example.myfitnesshub.data.entities.SleepRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface SleepDao {
    @Insert
    suspend fun insertSleepRecord(record: SleepRecord)

    @Query("SELECT * FROM sleep_records ORDER BY startDate DESC LIMIT 7")
    fun getLastSevenNights(): Flow<List<SleepRecord>>
}