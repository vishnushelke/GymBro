package com.pamu.gymbro.domain.model

import java.util.Date

data class WorkoutSession(
    val id: Long = 1L,
    val planId: Long,
    val dayId: Long,
    val startTime: Date,
    val currentExerciseIndex: Int,
    val currentSetNumber: Int,
    val totalRepsCompleted: Int,
    val totalWeightLifted: Double,
    val isPaused: Boolean = false
)
