package com.pamu.gymbro.domain.usecase.exercise

import com.pamu.gymbro.domain.model.Exercise
import com.pamu.gymbro.domain.repository.ExerciseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetExercisesUseCase @Inject constructor(
    private val repository: ExerciseRepository
) {
    operator fun invoke(categoryId: Long? = null): Flow<List<Exercise>> {
        return if (categoryId == null) {
            repository.getAllExercises()
        } else {
            repository.getExercisesByCategory(categoryId)
        }
    }
}
