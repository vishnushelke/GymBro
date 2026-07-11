package com.pamu.gymbro.domain.usecase.progress

import com.pamu.gymbro.domain.model.ProgressEntry
import com.pamu.gymbro.domain.repository.ProgressRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProgressEntriesUseCase @Inject constructor(
    private val repository: ProgressRepository
) {
    operator fun invoke(): Flow<List<ProgressEntry>> = repository.getAllProgressEntries()
}
