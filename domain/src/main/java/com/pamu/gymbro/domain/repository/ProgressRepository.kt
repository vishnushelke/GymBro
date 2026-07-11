package com.pamu.gymbro.domain.repository

import com.pamu.gymbro.domain.model.ProgressEntry
import kotlinx.coroutines.flow.Flow

interface ProgressRepository {
    fun getAllProgressEntries(): Flow<List<ProgressEntry>>
    suspend fun insertProgressEntry(entry: ProgressEntry)
    suspend fun deleteProgressEntry(id: Long)
}
