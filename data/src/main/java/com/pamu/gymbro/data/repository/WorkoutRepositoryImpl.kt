package com.pamu.gymbro.data.repository

import com.pamu.gymbro.data.local.dao.WorkoutDao
import com.pamu.gymbro.data.mapper.toDomain
import com.pamu.gymbro.data.mapper.toEntity
import com.pamu.gymbro.domain.model.WorkoutDay
import com.pamu.gymbro.domain.model.WorkoutPlan
import com.pamu.gymbro.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
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

    override fun getWorkoutDaysForPlan(planId: Long): Flow<List<WorkoutDay>> {
        return dao.getWorkoutDaysForPlan(planId).map { dayEntities ->
            dayEntities.map { it.toDomain() }
        }
    }

    override suspend fun insertWorkoutPlan(plan: WorkoutPlan, days: List<WorkoutDay>) {
        val planId = dao.insertWorkoutPlan(plan.toEntity())
        
        days.forEach { day ->
            val dayId = dao.insertWorkoutDays(listOf(day.toEntity().copy(workoutPlanId = planId))).first()
            val exercises = day.exercises.map { it.toEntity().copy(workoutDayId = dayId) }
            dao.insertWorkoutExercises(exercises)
        }
    }

    override suspend fun deleteWorkoutPlan(planId: Long) {
        dao.deleteWorkoutPlan(planId)
    }

    override suspend fun updateFavoriteStatus(id: Long, isFavorite: Boolean) {
        dao.updateFavoriteStatus(id, isFavorite)
    }

    override fun getFavoriteWorkoutPlans(): Flow<List<WorkoutPlan>> {
        return dao.getFavoriteWorkoutPlans().map { entities ->
            entities.map { it.toDomain() }
        }
    }
}
