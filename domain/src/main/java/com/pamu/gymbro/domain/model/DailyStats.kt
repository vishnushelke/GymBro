package com.pamu.gymbro.domain.model

data class DailyStats(
    val date: String,
    val caloriesBurned: Int = 0,
    val caloriesConsumed: Int = 0,
    val waterIntakeMl: Int = 0,
    val workoutCompleted: Boolean = false,
    val workoutProgressPercentage: Int = 0,
    val totalReps: Int = 0,
    val totalSets: Int = 0,
    val totalDurationMinutes: Int = 0
)
