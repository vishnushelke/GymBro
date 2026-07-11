package com.pamu.gymbro.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "workout_days",
    foreignKeys = [
        ForeignKey(
            entity = WorkoutPlanEntity::class,
            parentColumns = ["id"],
            childColumns = ["workoutPlanId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("workoutPlanId")]
)
data class WorkoutDayEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val workoutPlanId: Long,
    val dayNumber: Int,
    val title: String
)
