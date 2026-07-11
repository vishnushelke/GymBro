package com.pamu.gymbro.domain.model

data class DietPlan(
    val id: Long,
    val name: String,
    val goal: String,
    val calories: Int,
    val protein: Int,
    val carbs: Int,
    val fats: Int,
    val isFavorite: Boolean = false
)
