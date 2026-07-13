package com.pamu.gymbro.domain.usecase.diet

import com.pamu.gymbro.domain.model.DietPlan
import com.pamu.gymbro.domain.model.Meal
import com.pamu.gymbro.domain.repository.DietRepository
import javax.inject.Inject

class SaveDietPlanUseCase @Inject constructor(
    private val repository: DietRepository
) {
    suspend operator fun invoke(plan: DietPlan, meals: List<Meal>): Result<Unit> {
        if (plan.name.isBlank()) {
            return Result.failure(Exception("Plan name cannot be empty"))
        }
        if (meals.isEmpty()) {
            return Result.failure(Exception("Diet must have at least one meal"))
        }
        
        return try {
            repository.insertDietPlan(plan, meals)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
