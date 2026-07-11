package com.pamu.gymbro.domain.repository

import com.pamu.gymbro.domain.model.Exercise
import com.pamu.gymbro.domain.model.ExerciseCategory
import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {
    fun getAllCategories(): Flow<List<ExerciseCategory>>
    fun getAllExercises(): Flow<List<Exercise>>
    fun getExercisesByCategory(categoryId: Long): Flow<List<Exercise>>
    suspend fun getExerciseById(id: Long): Exercise?
    suspend fun insertCategories(categories: List<ExerciseCategory>)
    suspend fun insertExercises(exercises: List<Exercise>)
    suspend fun updateFavoriteStatus(id: Long, isFavorite: Boolean)
    fun getFavoriteExercises(): Flow<List<Exercise>>
    suspend fun importData()
}
