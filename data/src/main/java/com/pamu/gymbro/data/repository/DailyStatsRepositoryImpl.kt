package com.pamu.gymbro.data.repository

import com.pamu.gymbro.data.local.dao.DailyStatsDao
import com.pamu.gymbro.data.local.entity.DailyStatsEntity
import com.pamu.gymbro.domain.model.DailyStats
import com.pamu.gymbro.domain.repository.DailyStatsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DailyStatsRepositoryImpl @Inject constructor(
    private val dao: DailyStatsDao
) : DailyStatsRepository {

    override fun getStatsForDate(date: String): Flow<DailyStats?> {
        return dao.getStatsForDate(date).map { it?.toDomain() }
    }

    override suspend fun saveStats(stats: DailyStats) = withContext(Dispatchers.IO) {
        dao.insertStats(stats.toEntity())
    }

    override suspend fun addWater(date: String, amount: Int) = withContext(Dispatchers.IO) {
        val existing = dao.getStatsForDate(date).first()
        if (existing == null) {
            dao.insertStats(DailyStatsEntity(date = date, waterIntakeMl = amount))
        } else {
            dao.updateWaterIntake(date, amount)
        }
    }

    private fun DailyStatsEntity.toDomain() = DailyStats(
        date = date,
        caloriesBurned = caloriesBurned,
        caloriesConsumed = caloriesConsumed,
        waterIntakeMl = waterIntakeMl,
        workoutCompleted = workoutCompleted,
        workoutProgressPercentage = workoutProgressPercentage,
        totalReps = totalReps,
        totalSets = totalSets,
        totalDurationMinutes = totalDurationMinutes
    )

    private fun DailyStats.toEntity() = DailyStatsEntity(
        date = date,
        caloriesBurned = caloriesBurned,
        caloriesConsumed = caloriesConsumed,
        waterIntakeMl = waterIntakeMl,
        workoutCompleted = workoutCompleted,
        workoutProgressPercentage = workoutProgressPercentage,
        totalReps = totalReps,
        totalSets = totalSets,
        totalDurationMinutes = totalDurationMinutes
    )
}
