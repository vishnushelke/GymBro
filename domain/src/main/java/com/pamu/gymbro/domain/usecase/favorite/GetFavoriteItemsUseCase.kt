package com.pamu.gymbro.domain.usecase.favorite

import com.pamu.gymbro.domain.model.DietPlan
import com.pamu.gymbro.domain.model.Exercise
import com.pamu.gymbro.domain.model.WorkoutPlan
import com.pamu.gymbro.domain.repository.DietRepository
import com.pamu.gymbro.domain.repository.ExerciseRepository
import com.pamu.gymbro.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

data class FavoriteItems(
    val exercises: List<Exercise>,
    val workouts: List<WorkoutPlan>,
    val diets: List<DietPlan>
)

class GetFavoriteItemsUseCase @Inject constructor(
    private val exerciseRepository: ExerciseRepository,
    private val workoutRepository: WorkoutRepository,
    private val dietRepository: DietRepository
) {
    operator fun invoke(): Flow<FavoriteItems> {
        return combine(
            exerciseRepository.getFavoriteExercises(),
            workoutRepository.getFavoriteWorkoutPlans(),
            dietRepository.getFavoriteDietPlans()
        ) { exercises, workouts, diets ->
            FavoriteItems(exercises, workouts, diets)
        }
    }
}
