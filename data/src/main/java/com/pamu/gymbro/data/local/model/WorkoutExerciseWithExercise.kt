package com.pamu.gymbro.data.local.model

import androidx.room.Embedded
import androidx.room.Relation
import com.pamu.gymbro.data.local.entity.ExerciseEntity
import com.pamu.gymbro.data.local.entity.WorkoutExerciseEntity

data class WorkoutExerciseWithExercise(
    @Embedded val workoutExercise: WorkoutExerciseEntity,
    @Relation(
        parentColumn = "exerciseId",
        entityColumn = "id"
    )
    val exercise: ExerciseEntity
)
