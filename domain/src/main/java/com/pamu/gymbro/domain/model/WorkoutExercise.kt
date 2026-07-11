package com.pamu.gymbro.domain.model

data class WorkoutExercise(
    val id: Long,
    val workoutDayId: Long,
    val exerciseId: Long,
    val sets: Int,
    val reps: String,
    val restSeconds: Int,
    val exercise: Exercise? = null
)
