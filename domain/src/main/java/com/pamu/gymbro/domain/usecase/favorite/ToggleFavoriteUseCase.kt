package com.pamu.gymbro.domain.usecase.favorite

import com.pamu.gymbro.domain.repository.DietRepository
import com.pamu.gymbro.domain.repository.ExerciseRepository
import com.pamu.gymbro.domain.repository.WorkoutRepository
import javax.inject.Inject

enum class FavoriteType {
    EXERCISE, WORKOUT, DIET
}

class ToggleFavoriteUseCase @Inject constructor(
    private val exerciseRepository: ExerciseRepository,
    private val workoutRepository: WorkoutRepository,
    private val dietRepository: DietRepository
) {
    suspend operator fun invoke(id: Long, type: FavoriteType, isFavorite: Boolean) {
        when (type) {
            FavoriteType.EXERCISE -> exerciseRepository.updateFavoriteStatus(id, isFavorite)
            FavoriteType.WORKOUT -> workoutRepository.updateFavoriteStatus(id, isFavorite)
            FavoriteType.DIET -> dietRepository.updateFavoriteStatus(id, isFavorite)
        }
    }
}
