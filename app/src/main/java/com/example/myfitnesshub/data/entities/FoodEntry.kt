package com.example.myfitnesshub.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_entries")
data class FoodEntry(
    @PrimaryKey(autoGenerate = true) val entryId: Int = 0,
    val userId: Int,
    val date: Long,
    val mealType: String,
    val foodName: String,
    val quantity: Float,
    val calories: Int,
    val proteinGrams: Float,
    val carbGrams: Float,
    val fatGrams: Float
)
