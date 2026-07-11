package com.pamu.gymbro.domain.usecase.workout

import com.pamu.gymbro.domain.model.WorkoutPlan
import com.pamu.gymbro.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWorkoutPlansUseCase @Inject constructor(
    private val repository: WorkoutRepository
) {
    operator fun invoke(): Flow<List<WorkoutPlan>> = repository.getAllWorkoutPlans()
}
