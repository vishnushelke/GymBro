package com.pamu.gymbro.domain.repository

import com.pamu.gymbro.domain.model.DailyStats
import kotlinx.coroutines.flow.Flow

interface DailyStatsRepository {
    fun getStatsForDate(date: String): Flow<DailyStats?>
    suspend fun saveStats(stats: DailyStats)
    suspend fun addWater(date: String, amount: Int)
}
