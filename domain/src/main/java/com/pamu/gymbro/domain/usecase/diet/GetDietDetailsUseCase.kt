package com.pamu.gymbro.domain.usecase.diet

import com.pamu.gymbro.domain.model.DietPlan
import com.pamu.gymbro.domain.model.Meal
import com.pamu.gymbro.domain.repository.DietRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

data class DietDetails(
    val plan: DietPlan,
    val meals: List<Meal>
)

class GetDietDetailsUseCase @Inject constructor(
    private val repository: DietRepository
) {
    operator fun invoke(planId: Long): Flow<DietDetails?> {
        return combine(
            repository.getDietPlanById(planId),
            repository.getMealsForDietPlan(planId)
        ) { plan, meals ->
            plan?.let { DietDetails(it, meals) }
        }
    }
}
