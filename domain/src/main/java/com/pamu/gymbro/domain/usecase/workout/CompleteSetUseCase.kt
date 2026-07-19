package com.pamu.gymbro.domain.usecase.workout

import com.pamu.gymbro.domain.model.WorkoutSession
import com.pamu.gymbro.domain.repository.WorkoutSessionRepository
import javax.inject.Inject

class CompleteSetUseCase @Inject constructor(
    private val repository: WorkoutSessionRepository
) {
    suspend operator fun invoke(
        session: WorkoutSession,
        reps: Int,
        weight: Double,
        isLastSetInExercise: Boolean,
        isLastExerciseInWorkout: Boolean
    ) {
        val updatedSession = if (isLastExerciseInWorkout && isLastSetInExercise) {
            null // Workout finished, let FinishWorkoutUseCase handle it
        } else {
            session.copy(
                currentExerciseIndex = if (isLastSetInExercise) session.currentExerciseIndex + 1 else session.currentExerciseIndex,
                currentSetNumber = if (isLastSetInExercise) 1 else session.currentSetNumber + 1,
                totalRepsCompleted = session.totalRepsCompleted + reps,
                totalWeightLifted = session.totalWeightLifted + (reps * weight)
            )
        }

        if (updatedSession != null) {
            repository.saveSession(updatedSession)
        }
    }
}
