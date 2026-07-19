package com.pamu.gymbro.domain.usecase.workout

import com.pamu.gymbro.domain.repository.WorkoutRepository
import javax.inject.Inject

class UpdateWorkoutExerciseWeightUseCase @Inject constructor(
    private val repository: WorkoutRepository
) {
    suspend operator fun invoke(id: Long, weight: Double?, unit: String?): Result<Unit> {
        return repository.updateWorkoutExerciseWeight(id, weight, unit)
    }
}
