package com.pamu.gymbro.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_plans")
data class WorkoutPlanEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val level: String,
    val goal: String,
    val durationWeeks: Int,
    val description: String,
    val isFavorite: Boolean = false
)
