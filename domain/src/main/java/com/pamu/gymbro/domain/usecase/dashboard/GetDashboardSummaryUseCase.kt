package com.pamu.gymbro.domain.usecase.dashboard

import com.pamu.gymbro.domain.model.DashboardSummary
import com.pamu.gymbro.domain.repository.DietRepository
import com.pamu.gymbro.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GetDashboardSummaryUseCase @Inject constructor(
    private val workoutRepository: WorkoutRepository,
    private val dietRepository: DietRepository
) {
    operator fun invoke(): Flow<DashboardSummary> {
        return combine(
            workoutRepository.getAllWorkoutPlans().onStart { emit(emptyList()) },
            dietRepository.getAllDietPlans().onStart { emit(emptyList()) }
        ) { workouts, diets ->
            val activeWorkout = workouts.firstOrNull()?.name ?: "No active plan"
            val activeDiet = diets.firstOrNull()
            
            DashboardSummary(
                todayWorkout = activeWorkout,
                workoutCompletionPercentage = 0,
                currentWeightKg = 75.0f,
                currentStreakDays = 3,
                calorieGoal = activeDiet?.calories ?: 2500,
                caloriesConsumed = 0,
                waterIntakeMl = 1200,
                waterGoalMl = 2500
            )
        }
    }
}
