package com.pamu.gymbro.data.mapper

import com.pamu.gymbro.data.local.entity.ExerciseCategoryEntity
import com.pamu.gymbro.data.local.entity.ExerciseEntity
import com.pamu.gymbro.domain.model.Exercise
import com.pamu.gymbro.domain.model.ExerciseCategory

fun ExerciseEntity.toDomain(): Exercise {
    return Exercise(
        id = id,
        name = name,
        categoryId = categoryId,
        difficulty = difficulty,
        equipment = equipment,
        primaryMuscle = primaryMuscle,
        secondaryMuscles = secondaryMuscles,
        exerciseType = exerciseType,
        movementPattern = movementPattern,
        caloriesBurnedEstimate = caloriesBurnedEstimate,
        description = description,
        benefits = benefits,
        commonMistakes = commonMistakes,
        safetyWarnings = safetyWarnings,
        beginnerVariation = beginnerVariation,
        intermediateVariation = intermediateVariation,
        advancedVariation = advancedVariation,
        instructions = instructions,
        thumbnailUrl = thumbnailUrl,
        videoFrontUrl = videoFrontUrl,
        videoSideUrl = videoSideUrl,
        videoDuration = videoDuration,
        videoFps = videoFps,
        videoResolution = videoResolution,
        isFavorite = isFavorite
    )
}

fun Exercise.toEntity(): ExerciseEntity {
    return ExerciseEntity(
        id = id,
        name = name,
        categoryId = categoryId,
        difficulty = difficulty,
        equipment = equipment,
        primaryMuscle = primaryMuscle,
        secondaryMuscles = secondaryMuscles,
        exerciseType = exerciseType,
        movementPattern = movementPattern,
        caloriesBurnedEstimate = caloriesBurnedEstimate,
        description = description,
        benefits = benefits,
        commonMistakes = commonMistakes,
        safetyWarnings = safetyWarnings,
        beginnerVariation = beginnerVariation,
        intermediateVariation = intermediateVariation,
        advancedVariation = advancedVariation,
        instructions = instructions,
        thumbnailUrl = thumbnailUrl,
        videoFrontUrl = videoFrontUrl,
        videoSideUrl = videoSideUrl,
        videoDuration = videoDuration,
        videoFps = videoFps,
        videoResolution = videoResolution,
        isFavorite = isFavorite
    )
}

fun ExerciseCategoryEntity.toDomain(): ExerciseCategory {
    return ExerciseCategory(
        id = id,
        name = name
    )
}

fun ExerciseCategory.toEntity(): ExerciseCategoryEntity {
    return ExerciseCategoryEntity(
        id = id,
        name = name
    )
}
