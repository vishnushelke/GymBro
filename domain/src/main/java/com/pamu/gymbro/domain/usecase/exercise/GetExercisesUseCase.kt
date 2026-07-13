package com.pamu.gymbro.domain.usecase.exercise

import com.pamu.gymbro.domain.model.Exercise
import com.pamu.gymbro.domain.repository.ExerciseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetExercisesUseCase @Inject constructor(
    private val repository: ExerciseRepository
) {
    operator fun invoke(categoryId: Long? = null, query: String = ""): Flow<List<Exercise>> {
        val flow = if (categoryId == null) {
            repository.getAllExercises()
        } else {
            repository.getExercisesByCategory(categoryId)
        }

        return if (query.isBlank()) {
            flow
        } else {
            flow.map { exercises ->
                exercises.filter {
                    it.name.contains(query, ignoreCase = true) ||
                            it.primaryMuscle.contains(query, ignoreCase = true)
                }
            }
        }
    }
}
