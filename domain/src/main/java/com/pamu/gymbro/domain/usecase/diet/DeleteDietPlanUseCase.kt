package com.pamu.gymbro.domain.usecase.diet

import com.pamu.gymbro.domain.repository.DietRepository
import javax.inject.Inject

class DeleteDietPlanUseCase @Inject constructor(
    private val repository: DietRepository
) {
    suspend operator fun invoke(planId: Long) {
        repository.deleteDietPlan(planId)
    }
}
