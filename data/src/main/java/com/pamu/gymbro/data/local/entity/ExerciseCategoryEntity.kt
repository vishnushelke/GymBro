package com.pamu.gymbro.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_categories")
data class ExerciseCategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String
)
