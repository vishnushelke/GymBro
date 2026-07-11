package com.pamu.gymbro.domain.usecase.diet

import com.pamu.gymbro.domain.model.DietPlan
import com.pamu.gymbro.domain.repository.DietRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDietPlansUseCase @Inject constructor(
    private val repository: DietRepository
) {
    operator fun invoke(): Flow<List<DietPlan>> = repository.getAllDietPlans()
}
