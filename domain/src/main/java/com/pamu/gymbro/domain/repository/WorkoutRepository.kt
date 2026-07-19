package com.pamu.gymbro.domain.repository

import com.pamu.gymbro.domain.model.WorkoutDay
import com.pamu.gymbro.domain.model.WorkoutPlan
import kotlinx.coroutines.flow.Flow

interface WorkoutRepository {
    fun getAllWorkoutPlans(): Flow<List<WorkoutPlan>>
    fun getWorkoutPlanById(planId: Long): Flow<WorkoutPlan?>
    fun getWorkoutDayById(dayId: Long): Flow<WorkoutDay?>
    fun getWorkoutDaysForPlan(planId: Long): Flow<List<WorkoutDay>>
    suspend fun insertWorkoutPlan(plan: WorkoutPlan, days: List<WorkoutDay>)
    suspend fun deleteWorkoutPlan(planId: Long)
    suspend fun updateFavoriteStatus(id: Long, isFavorite: Boolean)
    suspend fun updateWorkoutExerciseWeight(id: Long, weight: Double?, unit: String?): Result<Unit>
    fun getFavoriteWorkoutPlans(): Flow<List<WorkoutPlan>>
}
