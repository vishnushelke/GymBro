package com.pamu.gymbro.domain.repository

import com.pamu.gymbro.domain.model.WorkoutSession
import kotlinx.coroutines.flow.Flow

interface WorkoutSessionRepository {
    fun getActiveSession(): Flow<WorkoutSession?>
    suspend fun saveSession(session: WorkoutSession)
    suspend fun deleteActiveSession()
}
