package com.example.myfitnesshub.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myfitnesshub.data.dao.*
import com.example.myfitnesshub.data.entities.*
import com.example.myfitnesshub.viewmodel.WorkoutPlan

@Database(
    entities = [
        User::class,
        UserMetric::class,
        WorkoutSession::class,
        ExerciseSet::class,
        FoodEntry::class,
        SleepRecord::class,
        WorkoutPlan::class
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract  class AppDatabase : RoomDatabase() {

    // Define abstract methods to access each DAO
    abstract fun userData(): UserDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun nutritionDao(): NutritionDao
    abstract fun sleepDao(): SleepDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            //if the instance is not null, the return it,
            //otherwise, create the data base
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "fitness_hub_database"
                )
                .fallbackToDestructiveMigration()
                .build()

                INSTANCE = instance
                instance

            }
        }
    }
}
