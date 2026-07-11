package com.pamu.gymbro.domain.model

data class WorkoutPlan(
    val id: Long,
    val name: String,
    val level: String,
    val goal: String,
    val durationWeeks: Int,
    val description: String,
    val isFavorite: Boolean = false
)
