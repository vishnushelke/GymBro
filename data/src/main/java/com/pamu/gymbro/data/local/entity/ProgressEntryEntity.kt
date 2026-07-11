package com.pamu.gymbro.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "progress_entries")
data class ProgressEntryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val date: Date,
    val weight: Float,
    val chest: Float,
    val waist: Float,
    val hips: Float,
    val arms: Float,
    val thighs: Float,
    val bodyFat: Float,
    val notes: String
)
