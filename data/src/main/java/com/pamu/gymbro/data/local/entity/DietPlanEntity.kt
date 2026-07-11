package com.pamu.gymbro.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diet_plans")
data class DietPlanEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val goal: String,
    val calories: Int,
    val protein: Int,
    val carbs: Int,
    val fats: Int,
    val isFavorite: Boolean = false
)
