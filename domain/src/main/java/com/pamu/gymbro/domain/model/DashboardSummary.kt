package com.pamu.gymbro.domain.model

data class DashboardSummary(
    val todayWorkout: String?,
    val workoutPlanId: Long?,
    val workoutDayId: Long?,
    val workoutCompletionPercentage: Int,
    val currentWeightKg: Float,
    val currentStreakDays: Int,
    val calorieGoal: Int,
    val caloriesConsumed: Int,
    val caloriesBurned: Int,
    val waterIntakeMl: Int,
    val waterGoalMl: Int = 2000
)
