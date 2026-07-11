package com.pamu.gymbro.domain.usecase.dashboard

import com.pamu.gymbro.domain.model.DashboardSummary
import com.pamu.gymbro.domain.repository.DietRepository
import com.pamu.gymbro.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetDashboardSummaryUseCase @Inject constructor(
    private val workoutRepository: WorkoutRepository,
    private val dietRepository: DietRepository
) {
    operator fun invoke(): Flow<DashboardSummary> {
        return combine(
            workoutRepository.getAllWorkoutPlans(),
            dietRepository.getAllDietPlans()
        ) { workouts, diets ->
            // In a real app, we'd fetch the active plan and current day's progress
            // Here we provide a summary based on available data or defaults
            val activeWorkout = workouts.firstOrNull()?.name ?: "No active plan"
            val activeDiet = diets.firstOrNull()
            
            DashboardSummary(
                todayWorkout = activeWorkout,
                workoutCompletionPercentage = 0, // Placeholder
                currentWeightKg = 75.0f, // Placeholder, would come from ProgressRepository
                currentStreakDays = 3, // Placeholder
                calorieGoal = activeDiet?.calories ?: 2500,
                caloriesConsumed = 0, // Placeholder
                waterIntakeMl = 1200, // Placeholder
                waterGoalMl = 2500
            )
        }
    }
}
