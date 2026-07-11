package com.pamu.gymbro.domain.model

import java.util.Date

data class ProgressEntry(
    val id: Long,
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
