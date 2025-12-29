package com.example.myfitnesshub.data

import android.content.Context
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.records.StepsRecord
import androidx.health.connect.client.request.AggregateRequest
import androidx.health.connect.client.time.TimeRangeFilter
import java.time.Instant

class HealthRepository(context: Context) {
    private val client by lazy { HealthConnectClient.getOrCreate(context) }

    suspend fun getTodaySteps(): Long {
        val startTime = Instant.now().minusSeconds(86400) // Last 24 hours
        val endTime = Instant.now()

        return try {
            val response = client.aggregate(
                AggregateRequest(
                    metrics = setOf(StepsRecord.COUNT_TOTAL),
                    timeRangeFilter = TimeRangeFilter.between(startTime, endTime)
                )
            )
            response[StepsRecord.COUNT_TOTAL] ?: 0L
        } catch (e: Exception) { 0L }
    }
}