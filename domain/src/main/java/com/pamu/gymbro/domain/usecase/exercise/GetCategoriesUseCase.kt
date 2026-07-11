package com.pamu.gymbro.domain.usecase.exercise

import com.pamu.gymbro.domain.model.ExerciseCategory
import com.pamu.gymbro.domain.repository.ExerciseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: ExerciseRepository
) {
    operator fun invoke(): Flow<List<ExerciseCategory>> = repository.getAllCategories()
}
