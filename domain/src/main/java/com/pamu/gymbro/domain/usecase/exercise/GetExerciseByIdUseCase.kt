package com.pamu.gymbro.domain.usecase.exercise

import com.pamu.gymbro.domain.model.Exercise
import com.pamu.gymbro.domain.repository.ExerciseRepository
import javax.inject.Inject

class GetExerciseByIdUseCase @Inject constructor(
    private val repository: ExerciseRepository
) {
    suspend operator fun invoke(id: Long): Exercise? = repository.getExerciseById(id)
}
