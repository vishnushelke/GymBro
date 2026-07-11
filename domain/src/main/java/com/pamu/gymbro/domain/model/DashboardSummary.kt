package com.pamu.gymbro.domain.model

data class DashboardSummary(
    val todayWorkout: String?,
    val workoutCompletionPercentage: Int,
    val currentWeightKg: Float,
    val currentStreakDays: Int,
    val calorieGoal: Int,
    val caloriesConsumed: Int,
    val waterIntakeMl: Int,
    val waterGoalMl: Int = 2000
)
