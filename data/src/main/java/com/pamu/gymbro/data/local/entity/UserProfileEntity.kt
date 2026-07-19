package com.pamu.gymbro.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey val id: Long = 1L,
    val name: String,
    val isVegetarian: Boolean,
    val experienceLevel: String,
    val fitnessGoal: String,
    val email: String = "",
    val phone: String = "",
    val age: Int = 0,
    val sex: String = "",
    val unitPreference: String = "KG",
    val isProfileCompleted: Boolean
)
