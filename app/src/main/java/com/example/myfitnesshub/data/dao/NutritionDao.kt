package com.example.myfitnesshub.data.dao

import androidx.room.*
import com.example.myfitnesshub.data.entities.FoodEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface NutritionDao {
    @Insert
    suspend fun insertFoodEntry(entry: FoodEntry)

    @Query("SELECT * FROM food_entries WHERE date >= :startOfDay ORDER BY date ASC")
    fun getFoodForDay(startOfDay: Long): Flow<List<FoodEntry>>

    @Delete
    suspend fun deleteFoodEntry(entry: FoodEntry)
}