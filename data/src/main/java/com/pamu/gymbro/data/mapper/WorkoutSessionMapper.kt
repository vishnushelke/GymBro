package com.pamu.gymbro.data.mapper

import com.pamu.gymbro.data.local.entity.WorkoutSessionEntity
import com.pamu.gymbro.domain.model.WorkoutSession

fun WorkoutSessionEntity.toDomain(): WorkoutSession {
    return WorkoutSession(
        id = id,
        planId = planId,
        dayId = dayId,
        startTime = startTime,
        currentExerciseIndex = currentExerciseIndex,
        currentSetNumber = currentSetNumber,
        totalRepsCompleted = totalRepsCompleted,
        totalWeightLifted = totalWeightLifted,
        isPaused = isPaused
    )
}

fun WorkoutSession.toEntity(): WorkoutSessionEntity {
    return WorkoutSessionEntity(
        id = id,
        planId = planId,
        dayId = dayId,
        startTime = startTime,
        currentExerciseIndex = currentExerciseIndex,
        currentSetNumber = currentSetNumber,
        totalRepsCompleted = totalRepsCompleted,
        totalWeightLifted = totalWeightLifted,
        isPaused = isPaused
    )
}
