package com.pamu.gymbro.domain.usecase.workout

import com.pamu.gymbro.domain.model.WorkoutDay
import com.pamu.gymbro.domain.model.WorkoutPlan
import com.pamu.gymbro.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

data class WorkoutDetails(
    val plan: WorkoutPlan,
    val days: List<WorkoutDay>
)

class GetWorkoutDetailsUseCase @Inject constructor(
    private val repository: WorkoutRepository
) {
    operator fun invoke(planId: Long): Flow<WorkoutDetails?> {
        return combine(
            repository.getWorkoutPlanById(planId),
            repository.getWorkoutDaysForPlan(planId)
        ) { plan, days ->
            plan?.let { WorkoutDetails(it, days) }
        }
    }
}
