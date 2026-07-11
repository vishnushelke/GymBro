package com.pamu.gymbro.domain.model

data class WorkoutDay(
    val id: Long,
    val workoutPlanId: Long,
    val dayNumber: Int,
    val title: String,
    val exercises: List<WorkoutExercise> = emptyList()
)
