package com.pamu.gymbro.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pamu.gymbro.data.local.entity.DietPlanEntity
import com.pamu.gymbro.data.local.entity.MealEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DietDao {
    @Query("SELECT * FROM diet_plans")
    fun getAllDietPlans(): Flow<List<DietPlanEntity>>

    @Query("SELECT * FROM diet_plans WHERE id = :id")
    fun getDietPlanById(id: Long): Flow<DietPlanEntity?>

    @Query("SELECT * FROM meals WHERE dietPlanId = :planId")
    fun getMealsForDietPlan(planId: Long): Flow<List<MealEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDietPlan(plan: DietPlanEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMeals(meals: List<MealEntity>)

    @Query("DELETE FROM meals WHERE dietPlanId = :planId")
    fun deleteMealsForPlan(planId: Long)

    @Query("DELETE FROM diet_plans WHERE id = :planId")
    fun deleteDietPlan(planId: Long)

    @Query("UPDATE diet_plans SET isFavorite = :isFavorite WHERE id = :id")
    fun updateFavoriteStatus(id: Long, isFavorite: Boolean)

    @Query("SELECT * FROM diet_plans WHERE isFavorite = 1")
    fun getFavoriteDietPlans(): Flow<List<DietPlanEntity>>
}
