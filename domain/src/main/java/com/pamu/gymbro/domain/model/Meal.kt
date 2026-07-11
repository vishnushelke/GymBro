package com.pamu.gymbro.domain.model

data class Meal(
    val id: Long,
    val dietPlanId: Long,
    val mealType: String,
    val name: String,
    val calories: Int,
    val protein: Int,
    val carbs: Int,
    val fats: Int
)
