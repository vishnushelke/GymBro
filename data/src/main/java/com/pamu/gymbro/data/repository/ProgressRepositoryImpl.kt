package com.pamu.gymbro.data.repository

import com.pamu.gymbro.data.local.dao.ProgressDao
import com.pamu.gymbro.data.mapper.toDomain
import com.pamu.gymbro.data.mapper.toEntity
import com.pamu.gymbro.domain.model.ProgressEntry
import com.pamu.gymbro.domain.repository.ProgressRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProgressRepositoryImpl @Inject constructor(
    private val dao: ProgressDao
) : ProgressRepository {

    override fun getAllProgressEntries(): Flow<List<ProgressEntry>> {
        return dao.getAllProgressEntries().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun insertProgressEntry(entry: ProgressEntry) {
        dao.insertProgressEntry(entry.toEntity())
    }

    override suspend fun deleteProgressEntry(id: Long) {
        dao.deleteProgressEntry(id)
    }
}
