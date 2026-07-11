package com.pamu.gymbro.domain.usecase.progress

import com.pamu.gymbro.domain.model.ProgressEntry
import com.pamu.gymbro.domain.repository.ProgressRepository
import javax.inject.Inject

class AddProgressEntryUseCase @Inject constructor(
    private val repository: ProgressRepository
) {
    suspend operator fun invoke(entry: ProgressEntry): Result<Unit> {
        if (entry.weight <= 0) {
            return Result.failure(Exception("Weight must be positive"))
        }
        return try {
            repository.insertProgressEntry(entry)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
