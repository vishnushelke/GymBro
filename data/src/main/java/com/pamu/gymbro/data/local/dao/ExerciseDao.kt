package com.pamu.gymbro.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pamu.gymbro.data.local.entity.ExerciseCategoryEntity
import com.pamu.gymbro.data.local.entity.ExerciseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exercise_categories")
    fun getAllCategories(): Flow<List<ExerciseCategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategories(categories: List<ExerciseCategoryEntity>)

    @Query("SELECT * FROM exercises")
    fun getAllExercises(): Flow<List<ExerciseEntity>>

    @Query("SELECT * FROM exercises WHERE categoryId = :categoryId")
    fun getExercisesByCategory(categoryId: Long): Flow<List<ExerciseEntity>>

    @Query("SELECT * FROM exercises WHERE id = :id")
    fun getExerciseById(id: Long): ExerciseEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExercises(exercises: List<ExerciseEntity>)

    @Query("UPDATE exercises SET isFavorite = :isFavorite WHERE id = :id")
    fun updateFavoriteStatus(id: Long, isFavorite: Boolean)

    @Query("SELECT * FROM exercises WHERE isFavorite = 1")
    fun getFavoriteExercises(): Flow<List<ExerciseEntity>>
}
