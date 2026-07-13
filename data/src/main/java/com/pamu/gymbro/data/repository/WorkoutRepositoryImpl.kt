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
        }
    }

    override fun getWorkoutPlanById(planId: Long): Flow<WorkoutPlan?> {
        return dao.getWorkoutPlanById(planId).map { it?.toDomain() }
    }

    override fun getWorkoutDayById(dayId: Long): Flow<WorkoutDay?> {
        return dao.getWorkoutDayById(dayId).map { it?.toDomain() }
    }

    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    override fun getWorkoutDaysForPlan(planId: Long): Flow<List<WorkoutDay>> {
        return dao.getWorkoutDaysForPlan(planId).flatMapLatest { dayEntities ->
            val dayFlows = dayEntities.map { dayEntity ->
                dao.getExercisesWithDetailsForDay(dayEntity.id).map { exercisesWithDetails ->
                    dayEntity.toDomain(exercisesWithDetails.map { it.toDomain() })
                }
            }
            if (dayFlows.isEmpty()) flowOf(emptyList())
            else combine(dayFlows) { it.toList() }
        }
    }

    override suspend fun insertWorkoutPlan(plan: WorkoutPlan, days: List<WorkoutDay>) = withContext(Dispatchers.IO) {
        val planId = dao.insertWorkoutPlan(plan.toEntity())
        
        // Clear existing days if editing
        if (plan.id > 0) {
            dao.deleteDaysForPlan(plan.id)
        }

        days.forEach { day ->
            val dayId = dao.insertWorkoutDays(listOf(day.toEntity().copy(workoutPlanId = planId))).first()
            val exercises = day.exercises.map { it.toEntity().copy(workoutDayId = dayId) }
            dao.insertWorkoutExercises(exercises)
        }
    }

    override suspend fun deleteWorkoutPlan(planId: Long) = withContext(Dispatchers.IO) {
        dao.deleteWorkoutPlan(planId)
    }

    override suspend fun updateFavoriteStatus(id: Long, isFavorite: Boolean) = withContext(Dispatchers.IO) {
        dao.updateFavoriteStatus(id, isFavorite)
    }

    override fun getFavoriteWorkoutPlans(): Flow<List<WorkoutPlan>> {
        return dao.getFavoriteWorkoutPlans().map { entities ->
            entities.map { it.toDomain() }
        }
    }
}
