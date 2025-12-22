package com.example.myfitnesshub.data.dao

import androidx.room.*
import com.example.myfitnesshub.data.entities.User
import com.example.myfitnesshub.data.entities.UserMetric
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM users WHERE userId = :id")
    suspend fun getUserById(id: Int): User?

    @Insert
    suspend fun insertMetric(metric: UserMetric)

    @Query("SELECT * FROM user_metrics WHERE userId = :userId ORDER BY date DESC")
    fun getUserMetrics(userId: Int): Flow<List<UserMetric>>
}