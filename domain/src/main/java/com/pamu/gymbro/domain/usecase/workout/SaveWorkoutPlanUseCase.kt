package com.pamu.gymbro.domain.usecase.workout

import com.pamu.gymbro.domain.model.WorkoutDay
import com.pamu.gymbro.domain.model.WorkoutPlan
import com.pamu.gymbro.domain.repository.WorkoutRepository
import javax.inject.Inject

class SaveWorkoutPlanUseCase @Inject constructor(
    private val repository: WorkoutRepository
) {
    suspend operator fun invoke(plan: WorkoutPlan, days: List<WorkoutDay>): Result<Unit> {
        if (plan.name.isBlank()) {
            return Result.failure(Exception("Plan name cannot be empty"))
        }
        if (days.isEmpty()) {
            return Result.failure(Exception("Workout must have at least one day"))
        }
        
        return try {
            repository.insertWorkoutPlan(plan, days)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
