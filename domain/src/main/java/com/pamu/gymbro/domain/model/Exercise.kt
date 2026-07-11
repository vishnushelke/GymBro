package com.pamu.gymbro.domain.model

data class Exercise(
    val id: Long,
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
