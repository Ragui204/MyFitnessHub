package com.example.myfitnesshub.data.dao

import androidx.room.*
import com.example.myfitnesshub.data.entities.ExerciseSet
import com.example.myfitnesshub.data.entities.WorkoutSession
import com.example.myfitnesshub.viewmodel.WorkoutPlan
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {
    @Query("SELECT * FROM workout_plans")
    fun getAllPlans(): Flow<List<WorkoutPlan>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlan(plan: WorkoutPlan)

    @Delete
    suspend fun deletePlan(plan: WorkoutPlan)

    @Insert
    suspend fun insertSession(session: WorkoutSession): Long // Returns the new ID

    @Insert
    suspend fun insertExerciseSet(exerciseSet: ExerciseSet)

    @Query("SELECT * FROM workout_sessions ORDER BY date DESC")
    fun getAllSessions(): Flow<List<WorkoutSession>>

    @Query("SELECT * FROM exercise_sets WHERE sessionId = :sessionId")
    fun getSetsForSession(sessionId: Int): Flow<List<ExerciseSet>>
}