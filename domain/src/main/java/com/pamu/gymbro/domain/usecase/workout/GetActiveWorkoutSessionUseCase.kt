package com.pamu.gymbro.domain.usecase.workout

import com.pamu.gymbro.domain.model.WorkoutSession
import com.pamu.gymbro.domain.repository.WorkoutSessionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetActiveWorkoutSessionUseCase @Inject constructor(
    private val repository: WorkoutSessionRepository
) {
    operator fun invoke(): Flow<WorkoutSession?> = repository.getActiveSession()
}
