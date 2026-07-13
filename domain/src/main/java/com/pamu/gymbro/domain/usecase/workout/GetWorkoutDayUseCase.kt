package com.pamu.gymbro.domain.usecase.workout

import com.pamu.gymbro.domain.model.WorkoutDay
import com.pamu.gymbro.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWorkoutDayUseCase @Inject constructor(
    private val repository: WorkoutRepository
) {
    operator fun invoke(dayId: Long): Flow<WorkoutDay?> = repository.getWorkoutDayById(dayId)
}
