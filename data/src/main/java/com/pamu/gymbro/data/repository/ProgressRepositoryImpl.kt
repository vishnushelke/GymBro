package com.pamu.gymbro.data.repository

import com.pamu.gymbro.data.local.dao.ProgressDao
import com.pamu.gymbro.data.mapper.toDomain
import com.pamu.gymbro.data.mapper.toEntity
import com.pamu.gymbro.domain.model.ProgressEntry
import com.pamu.gymbro.domain.repository.ProgressRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProgressRepositoryImpl @Inject constructor(
    private val dao: ProgressDao
) : ProgressRepository {

    override fun getAllProgressEntries(): Flow<List<ProgressEntry>> {
        return dao.getAllProgressEntries().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun insertProgressEntry(entry: ProgressEntry) = withContext(Dispatchers.IO) {
        dao.insertProgressEntry(entry.toEntity())
    }

    override suspend fun deleteProgressEntry(id: Long) = withContext(Dispatchers.IO) {
        dao.deleteProgressEntry(id)
    }
}
