package com.pamu.gymbro.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "daily_stats")
data class DailyStatsEntity(
    @PrimaryKey val date: String, // Format: yyyy-MM-dd
    val caloriesBurned: Int = 0,
    val caloriesConsumed: Int = 0,
    val waterIntakeMl: Int = 0,
    val workoutCompleted: Boolean = false,
    val workoutProgressPercentage: Int = 0,
    val totalReps: Int = 0,
    val totalSets: Int = 0,
    val totalDurationMinutes: Int = 0
)
