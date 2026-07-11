package com.pamu.gymbro.data.local.json

import kotlinx.serialization.Serializable

@Serializable
data class ExerciseJson(
    val exerciseId: Long,
    val name: String,
    val category: String,
    val difficulty: String,
    val equipment: String,
    val primaryMuscle: String,
    val secondaryMuscles: String = "",
    val exerciseType: String = "",
    val movementPattern: String = "",
    val caloriesBurnedEstimate: Int = 0,
    val description: String = "",
    val benefits: String = "",
    val commonMistakes: String = "",
    val safetyWarnings: String = "",
    val beginnerVariation: String = "",
    val intermediateVariation: String = "",
    val advancedVariation: String = "",
    val instructions: String = "",
    val thumbnailUrl: String = "",
    val videoFrontUrl: String = "",
    val videoSideUrl: String = "",
    val videoDuration: Int = 0,
    val videoFps: Int = 30,
    val videoResolution: String = "1080p"
)

@Serializable
data class CategoryJson(
    val id: Long,
    val name: String
)

@Serializable
data class ImportDataJson(
    val categories: List<CategoryJson>,
    val exercises: List<ExerciseJson>
)
