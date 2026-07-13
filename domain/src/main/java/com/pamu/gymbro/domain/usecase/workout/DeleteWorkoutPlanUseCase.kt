package com.pamu.gymbro.domain.usecase.workout

import com.pamu.gymbro.domain.repository.WorkoutRepository
import javax.inject.Inject

class DeleteWorkoutPlanUseCase @Inject constructor(
    private val repository: WorkoutRepository
) {
    suspend operator fun invoke(planId: Long) {
        repository.deleteWorkoutPlan(planId)
    }
}
