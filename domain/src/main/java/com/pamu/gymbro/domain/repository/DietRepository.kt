package com.pamu.gymbro.domain.repository

import com.pamu.gymbro.domain.model.DietPlan
import com.pamu.gymbro.domain.model.Meal
import kotlinx.coroutines.flow.Flow

interface DietRepository {
    fun getAllDietPlans(): Flow<List<DietPlan>>
    fun getDietPlanById(id: Long): Flow<DietPlan?>
    fun getMealsForDietPlan(planId: Long): Flow<List<Meal>>
    suspend fun insertDietPlan(plan: DietPlan, meals: List<Meal>)
    suspend fun deleteDietPlan(planId: Long)
    suspend fun updateFavoriteStatus(id: Long, isFavorite: Boolean)
    fun getFavoriteDietPlans(): Flow<List<DietPlan>>
}
