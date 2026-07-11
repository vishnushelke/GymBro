package com.pamu.gymbro.domain.usecase.exercise

import com.pamu.gymbro.domain.repository.ExerciseRepository
import javax.inject.Inject

class ImportDataUseCase @Inject constructor(
    private val repository: ExerciseRepository
) {
    suspend operator fun invoke() = repository.importData()
}
