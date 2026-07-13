package com.pamu.gymbro.data.repository

import com.pamu.gymbro.data.local.dao.DietDao
import com.pamu.gymbro.data.mapper.toDomain
import com.pamu.gymbro.data.mapper.toEntity
import com.pamu.gymbro.domain.model.DietPlan
import com.pamu.gymbro.domain.model.Meal
import com.pamu.gymbro.domain.repository.DietRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DietRepositoryImpl @Inject constructor(
    private val dao: DietDao
) : DietRepository {

    override fun getAllDietPlans(): Flow<List<DietPlan>> {
        return dao.getAllDietPlans().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getDietPlanById(id: Long): Flow<DietPlan?> {
        return dao.getDietPlanById(id).map { it?.toDomain() }
    }

    override fun getMealsForDietPlan(planId: Long): Flow<List<Meal>> {
        return dao.getMealsForDietPlan(planId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun insertDietPlan(plan: DietPlan, meals: List<Meal>) = withContext(Dispatchers.IO) {
        val planId = dao.insertDietPlan(plan.toEntity())
        dao.deleteMealsForPlan(planId)
        dao.insertMeals(meals.map { it.toEntity().copy(dietPlanId = planId, id = 0) }) // Ensure id=0 for new meals
    }

    override suspend fun deleteDietPlan(planId: Long) = withContext(Dispatchers.IO) {
        dao.deleteDietPlan(planId)
    }

    override suspend fun updateFavoriteStatus(id: Long, isFavorite: Boolean) = withContext(Dispatchers.IO) {
        dao.updateFavoriteStatus(id, isFavorite)
    }

    override fun getFavoriteDietPlans(): Flow<List<DietPlan>> {
        return dao.getFavoriteDietPlans().map { entities ->
            entities.map { it.toDomain() }
        }
    }
}
