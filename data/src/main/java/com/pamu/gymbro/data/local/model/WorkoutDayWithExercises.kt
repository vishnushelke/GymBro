package com.pamu.gymbro.data.local.model

import androidx.room.Embedded
import androidx.room.Relation
import com.pamu.gymbro.data.local.entity.WorkoutDayEntity
import com.pamu.gymbro.data.local.entity.WorkoutExerciseEntity

data class WorkoutDayWithExercises(
    @Embedded val workoutDay: WorkoutDayEntity,
    @Relation(
        entity = WorkoutExerciseEntity::class,
        parentColumn = "id",
        entityColumn = "workoutDayId"
    )
    val workoutExercises: List<WorkoutExerciseWithExercise>
)
