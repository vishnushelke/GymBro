package com.pamu.gymbro.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey val id: Long = 1L, // Single user profile
    val name: String,
    val age: Int,
    val gender: String,
    val heightCm: Float,
    val weightKg: Float,
    val fitnessGoal: String,
    val experienceLevel: String,
    val workoutDurationMinutes: Int,
    val workoutDaysPerWeek: Int,
    val injuries: String,
    val availableEquipment: String
)
