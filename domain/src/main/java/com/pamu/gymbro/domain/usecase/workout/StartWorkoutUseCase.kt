package com.pamu.gymbro.domain.usecase.workout

import com.pamu.gymbro.domain.model.WorkoutSession
import com.pamu.gymbro.domain.repository.WorkoutSessionRepository
import java.util.Date
import javax.inject.Inject

class StartWorkoutUseCase @Inject constructor(
    private val repository: WorkoutSessionRepository
) {
    suspend operator fun invoke(planId: Long, dayId: Long) {
        val newSession = WorkoutSession(
            planId = planId,
            dayId = dayId,
            startTime = Date(),
            currentExerciseIndex = 0,
            currentSetNumber = 1,
            totalRepsCompleted = 0,
            totalWeightLifted = 0.0
        )
        repository.saveSession(newSession)
    }
}
