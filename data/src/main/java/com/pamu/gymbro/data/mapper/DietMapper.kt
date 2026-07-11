package com.pamu.gymbro.data.mapper

import com.pamu.gymbro.data.local.entity.DietPlanEntity
import com.pamu.gymbro.data.local.entity.MealEntity
import com.pamu.gymbro.domain.model.DietPlan
import com.pamu.gymbro.domain.model.Meal

fun DietPlanEntity.toDomain(): DietPlan {
    return DietPlan(
        id = id,
        name = name,
        goal = goal,
        calories = calories,
        protein = protein,
        carbs = carbs,
        fats = fats
    )
}

fun DietPlan.toEntity(): DietPlanEntity {
    return DietPlanEntity(
        id = id,
        name = name,
        goal = goal,
        calories = calories,
        protein = protein,
        carbs = carbs,
        fats = fats
    )
}

fun MealEntity.toDomain(): Meal {
    return Meal(
        id = id,
        dietPlanId = dietPlanId,
        mealType = mealType,
        name = name,
        calories = calories,
        protein = protein,
        carbs = carbs,
        fats = fats
    )
}

fun Meal.toEntity(): MealEntity {
    return MealEntity(
        id = id,
        dietPlanId = dietPlanId,
        mealType = mealType,
        name = name,
        calories = calories,
        protein = protein,
        carbs = carbs,
        fats = fats
    )
}
