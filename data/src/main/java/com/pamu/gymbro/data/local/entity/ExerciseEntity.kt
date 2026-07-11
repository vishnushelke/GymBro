package com.pamu.gymbro.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "exercises",
    foreignKeys = [
        ForeignKey(
            entity = ExerciseCategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("categoryId")]
)
data class ExerciseEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val categoryId: Long,
    val difficulty: String,
    val equipment: String,
    val primaryMuscle: String,
    val secondaryMuscles: String,
    val exerciseType: String,
    val movementPattern: String,
    val caloriesBurnedEstimate: Int,
    val description: String,
    val benefits: String,
    val commonMistakes: String,
    val safetyWarnings: String,
    val beginnerVariation: String,
    val intermediateVariation: String,
    val advancedVariation: String,
    val instructions: String,
    val thumbnailUrl: String,
    val videoFrontUrl: String,
    val videoSideUrl: String,
    val videoDuration: Int,
    val videoFps: Int,
    val videoResolution: String,
    val isFavorite: Boolean = false
)
