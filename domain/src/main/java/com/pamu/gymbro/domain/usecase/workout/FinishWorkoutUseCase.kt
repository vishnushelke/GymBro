package com.pamu.gymbro.domain.usecase.workout

import com.pamu.gymbro.domain.model.DailyStats
import com.pamu.gymbro.domain.model.WorkoutSession
import com.pamu.gymbro.domain.repository.DailyStatsRepository
import com.pamu.gymbro.domain.repository.ProgressRepository
import com.pamu.gymbro.domain.repository.WorkoutSessionRepository
import kotlinx.coroutines.flow.first
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class FinishWorkoutUseCase @Inject constructor(
    private val sessionRepository: WorkoutSessionRepository,
    private val statsRepository: DailyStatsRepository,
    private val progressRepository: ProgressRepository
) {
    suspend operator fun invoke(session: WorkoutSession): DailyStats {
        val endTime = Date()
        val durationMillis = endTime.time - session.startTime.time
        val durationMinutes = (durationMillis / (1000 * 60)).toInt().coerceAtLeast(1)
        
        val userWeight = progressRepository.getAllProgressEntries().first().firstOrNull()?.weight ?: 75.0f
        
        // Simple MET formula: Calories = MET * weight(kg) * time(hr)
        // Average gym MET is ~5.0
        val caloriesBurned = (5.0 * userWeight * (durationMinutes / 60.0)).toInt()
        
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val dateKey = dateFormat.format(endTime)
        
        val existingStats = statsRepository.getStatsForDate(dateKey).first() ?: DailyStats(dateKey)
        
        val updatedStats = existingStats.copy(
            caloriesBurned = existingStats.caloriesBurned + caloriesBurned,
            workoutCompleted = true,
            workoutProgressPercentage = 100,
            totalReps = existingStats.totalReps + session.totalRepsCompleted,
            totalSets = existingStats.totalSets + session.currentSetNumber, // Approximate
            totalDurationMinutes = existingStats.totalDurationMinutes + durationMinutes
        )
        
        statsRepository.saveStats(updatedStats)
        sessionRepository.deleteActiveSession()
        
        return updatedStats
    }
}
