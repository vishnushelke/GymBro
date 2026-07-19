package com.pamu.gymbro.data.repository

import com.pamu.gymbro.data.local.dao.WorkoutSessionDao
import com.pamu.gymbro.data.mapper.toDomain
import com.pamu.gymbro.data.mapper.toEntity
import com.pamu.gymbro.domain.model.WorkoutSession
import com.pamu.gymbro.domain.repository.WorkoutSessionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WorkoutSessionRepositoryImpl @Inject constructor(
    private val dao: WorkoutSessionDao
) : WorkoutSessionRepository {

    override fun getActiveSession(): Flow<WorkoutSession?> {
        return dao.getActiveSession().map { it?.toDomain() }
    }

    override suspend fun saveSession(session: WorkoutSession) = withContext(Dispatchers.IO) {
        dao.insertSession(session.toEntity())
    }

    override suspend fun deleteActiveSession() = withContext(Dispatchers.IO) {
        dao.deleteActiveSession()
    }
}
