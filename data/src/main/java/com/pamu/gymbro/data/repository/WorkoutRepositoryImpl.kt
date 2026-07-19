package com.pamu.gymbro.data.repository

import com.pamu.gymbro.data.local.dao.WorkoutDao
import com.pamu.gymbro.data.mapper.toDomain
import com.pamu.gymbro.data.mapper.toEntity
import com.pamu.gymbro.domain.model.WorkoutDay
import com.pamu.gymbro.domain.model.WorkoutPlan
import com.pamu.gymbro.domain.repository.WorkoutRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WorkoutRepositoryImpl @Inject constructor(
    private val dao: WorkoutDao
) : WorkoutRepository {

    override fun getAllWorkoutPlans(): Flow<List<WorkoutPlan>> {
        return dao.getAllWorkoutPlans().map { entities ->
            entities.map { it.toDomain() }
        }.flowOn(Dispatchers.IO)
    }

    override fun getWorkoutPlanById(planId: Long): Flow<WorkoutPlan?> {
        return dao.getWorkoutPlanById(planId).map { it?.toDomain() }.flowOn(Dispatchers.IO)
    }

    override fun getWorkoutDayById(dayId: Long): Flow<WorkoutDay?> {
        return dao.getWorkoutDayWithExercisesById(dayId).map { it?.toDomain() }.flowOn(Dispatchers.IO)
    }

    override fun getWorkoutDaysForPlan(planId: Long): Flow<List<WorkoutDay>> {
        return dao.getWorkoutDaysWithExercisesForPlan(planId).map { list ->
            list.map { it.toDomain() }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun insertWorkoutPlan(plan: WorkoutPlan, days: List<WorkoutDay>) {
        withContext(Dispatchers.IO) {
            if (plan.id > 0) {
                dao.deleteDaysForPlan(plan.id)
            }

            val planId = dao.insertWorkoutPlan(plan.toEntity())
            
            days.forEach { day ->
                val dayId = dao.insertWorkoutDays(listOf(day.toEntity().copy(workoutPlanId = planId))).first()
                val exercises = day.exercises.map { it.toEntity().copy(workoutDayId = dayId) }
                dao.insertWorkoutExercises(exercises)
            }
        }
    }

    override suspend fun deleteWorkoutPlan(planId: Long) = withContext(Dispatchers.IO) {
        dao.deleteWorkoutPlan(planId)
    }

    override suspend fun updateFavoriteStatus(id: Long, isFavorite: Boolean) = withContext(Dispatchers.IO) {
        dao.updateFavoriteStatus(id, isFavorite)
    }

    override suspend fun updateWorkoutExerciseWeight(id: Long, weight: Double?, unit: String?): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            dao.updateExerciseWeight(id, weight, unit)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getFavoriteWorkoutPlans(): Flow<List<WorkoutPlan>> {
        return dao.getFavoriteWorkoutPlans().map { entities ->
            entities.map { it.toDomain() }
        }.flowOn(Dispatchers.IO)
    }
}
