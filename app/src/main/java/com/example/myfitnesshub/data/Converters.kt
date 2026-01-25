package com.example.myfitnesshub.data

import androidx.room.TypeConverter
import com.example.myfitnesshub.viewmodel.Exercise
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromExerciseList(value: List<Exercise>) : String = Gson().toJson(value)

    @TypeConverter
    fun toExerciseList(value: String): List<Exercise> {
        val listType = object :  TypeToken<List<Exercise>>() {}.type
        return Gson().fromJson(value, listType)

    }
}
