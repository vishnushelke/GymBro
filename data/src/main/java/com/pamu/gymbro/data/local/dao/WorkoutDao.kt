package com.pamu.gymbro.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.pamu.gymbro.data.local.entity.WorkoutDayEntity
import com.pamu.gymbro.data.local.entity.WorkoutExerciseEntity
import com.pamu.gymbro.data.local.entity.WorkoutPlanEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {
    @Query("SELECT * FROM workout_plans")
    fun getAllWorkoutPlans(): Flow<List<WorkoutPlanEntity>>

    @Query("SELECT * FROM workout_plans WHERE id = :planId")
    fun getWorkoutPlanById(planId: Long): Flow<WorkoutPlanEntity?>

    @Query("SELECT * FROM workout_days WHERE workoutPlanId = :planId")
    fun getWorkoutDaysForPlan(planId: Long): Flow<List<WorkoutDayEntity>>

    @Query("SELECT * FROM workout_exercises WHERE workoutDayId = :dayId")
    fun getExercisesForDay(dayId: Long): Flow<List<WorkoutExerciseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkoutPlan(plan: WorkoutPlanEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkoutDays(days: List<WorkoutDayEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkoutExercises(exercises: List<WorkoutExerciseEntity>)

    @Transaction
    @Query("DELETE FROM workout_plans WHERE id = :planId")
    fun deleteWorkoutPlan(planId: Long)

    @Query("UPDATE workout_plans SET isFavorite = :isFavorite WHERE id = :id")
    fun updateFavoriteStatus(id: Long, isFavorite: Boolean)

    @Query("SELECT * FROM workout_plans WHERE isFavorite = 1")
    fun getFavoriteWorkoutPlans(): Flow<List<WorkoutPlanEntity>>
}
