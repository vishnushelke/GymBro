package com.pamu.gymbro.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "active_workout_sessions")
data class WorkoutSessionEntity(
    @PrimaryKey val id: Long = 1L, // Only one active session at a time
    val planId: Long,
    val dayId: Long,
    val startTime: Date,
    val currentExerciseIndex: Int,
    val currentSetNumber: Int,
    val totalRepsCompleted: Int,
    val totalWeightLifted: Double,
    val isPaused: Boolean = false
)
