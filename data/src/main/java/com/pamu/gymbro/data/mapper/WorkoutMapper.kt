package com.pamu.gymbro.data.mapper

import com.pamu.gymbro.data.local.entity.WorkoutDayEntity
import com.pamu.gymbro.data.local.entity.WorkoutExerciseEntity
import com.pamu.gymbro.data.local.entity.WorkoutPlanEntity
import com.pamu.gymbro.domain.model.Exercise
import com.pamu.gymbro.domain.model.WorkoutDay
import com.pamu.gymbro.domain.model.WorkoutExercise
import com.pamu.gymbro.domain.model.WorkoutPlan

fun WorkoutPlanEntity.toDomain(): WorkoutPlan {
    return WorkoutPlan(
        id = id,
        name = name,
        level = level,
        goal = goal,
        durationWeeks = durationWeeks,
        description = description
    )
}

fun WorkoutPlan.toEntity(): WorkoutPlanEntity {
    return WorkoutPlanEntity(
        id = id,
        name = name,
        level = level,
        goal = goal,
        durationWeeks = durationWeeks,
        description = description
    )
}

fun WorkoutDayEntity.toDomain(exercises: List<WorkoutExercise> = emptyList()): WorkoutDay {
    return WorkoutDay(
        id = id,
        workoutPlanId = workoutPlanId,
        dayNumber = dayNumber,
        title = title,
        exercises = exercises
    )
}

fun com.pamu.gymbro.data.local.model.WorkoutDayWithExercises.toDomain(): WorkoutDay {
    return workoutDay.toDomain(workoutExercises.map { it.toDomain() })
}

fun WorkoutDay.toEntity(): WorkoutDayEntity {
    return WorkoutDayEntity(
        id = id,
        workoutPlanId = workoutPlanId,
        dayNumber = dayNumber,
        title = title
    )
}

fun WorkoutExerciseEntity.toDomain(exercise: Exercise? = null): WorkoutExercise {
    return WorkoutExercise(
        id = id,
        workoutDayId = workoutDayId,
        exerciseId = exerciseId,
        sets = sets,
        reps = reps,
        restSeconds = restSeconds,
        exercise = exercise,
        comfortableWeight = comfortableWeight,
        weightUnit = weightUnit
    )
}

fun com.pamu.gymbro.data.local.model.WorkoutExerciseWithExercise.toDomain(): WorkoutExercise {
    return workoutExercise.toDomain(exercise?.toDomain())
}

fun WorkoutExercise.toEntity(): WorkoutExerciseEntity {
    return WorkoutExerciseEntity(
        id = id,
        workoutDayId = workoutDayId,
        exerciseId = exerciseId,
        sets = sets,
        reps = reps,
        restSeconds = restSeconds,
        comfortableWeight = comfortableWeight,
        weightUnit = weightUnit
    )
}
