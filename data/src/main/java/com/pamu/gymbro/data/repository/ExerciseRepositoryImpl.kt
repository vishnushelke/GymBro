package com.pamu.gymbro.data.repository

import com.pamu.gymbro.data.local.asset.AssetReader
import com.pamu.gymbro.data.local.dao.ExerciseDao
import com.pamu.gymbro.data.local.json.ImportDataJson
import com.pamu.gymbro.data.mapper.toDomain
import com.pamu.gymbro.data.mapper.toEntity
import com.pamu.gymbro.domain.model.Exercise
import com.pamu.gymbro.domain.model.ExerciseCategory
import com.pamu.gymbro.domain.repository.ExerciseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

class ExerciseRepositoryImpl @Inject constructor(
    private val dao: ExerciseDao,
    private val assetReader: AssetReader
) : ExerciseRepository {

    private val json = Json { ignoreUnknownKeys = true }

    override fun getAllCategories(): Flow<List<ExerciseCategory>> {
        return dao.getAllCategories().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getAllExercises(): Flow<List<Exercise>> {
        return dao.getAllExercises().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getExercisesByCategory(categoryId: Long): Flow<List<Exercise>> {
        return dao.getExercisesByCategory(categoryId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getExerciseById(id: Long): Exercise? = withContext(Dispatchers.IO) {
        dao.getExerciseById(id)?.toDomain()
    }

    override suspend fun insertCategories(categories: List<ExerciseCategory>) = withContext(Dispatchers.IO) {
        dao.insertCategories(categories.map { it.toEntity() })
    }

    override suspend fun insertExercises(exercises: List<Exercise>) = withContext(Dispatchers.IO) {
        dao.insertExercises(exercises.map { it.toEntity() })
    }

    override suspend fun updateFavoriteStatus(id: Long, isFavorite: Boolean) = withContext(Dispatchers.IO) {
        dao.updateFavoriteStatus(id, isFavorite)
    }

    override fun getFavoriteExercises(): Flow<List<Exercise>> {
        return dao.getFavoriteExercises().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun importData() = withContext(Dispatchers.IO) {
        val jsonString = assetReader.readAsset("data.json") ?: return@withContext
        try {
            val importData = json.decodeFromString<ImportDataJson>(jsonString)
            
            val categoryEntities = importData.categories.map { 
                com.pamu.gymbro.data.local.entity.ExerciseCategoryEntity(it.id, it.name.uppercase().trim())
            }
            dao.insertCategories(categoryEntities)

            // Deduplicate by normalized name
            val uniqueExercises = importData.exercises
                .distinctBy { it.name.lowercase().replace("-", " ").replace("_", " ").trim() }

            val exerciseEntities = uniqueExercises.map { 
                com.pamu.gymbro.data.local.entity.ExerciseEntity(
                    id = it.exerciseId,
                    name = it.name.trim(),
                    categoryId = categoryEntities.find { c -> c.name == it.category.uppercase().trim() }?.id ?: 0,
                    difficulty = it.difficulty,
                    equipment = it.equipment,
                    primaryMuscle = it.primaryMuscle,
                    secondaryMuscles = it.secondaryMuscles,
                    exerciseType = it.exerciseType,
                    movementPattern = it.movementPattern,
                    caloriesBurnedEstimate = it.caloriesBurnedEstimate,
                    description = it.description,
                    benefits = it.benefits,
                    commonMistakes = it.commonMistakes,
                    safetyWarnings = it.safetyWarnings,
                    beginnerVariation = it.beginnerVariation,
                    intermediateVariation = it.intermediateVariation,
                    advancedVariation = it.advancedVariation,
                    instructions = it.instructions,
                    thumbnailUrl = it.thumbnailUrl,
                    videoFrontUrl = it.videoFrontUrl,
                    videoSideUrl = it.videoSideUrl,
                    videoDuration = it.videoDuration,
                    videoFps = it.videoFps,
                    videoResolution = it.videoResolution,
                    isFavorite = false
                )
            }
            dao.insertExercises(exerciseEntities)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
