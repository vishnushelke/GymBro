package com.pamu.gymbro.domain.usecase.dashboard

import com.pamu.gymbro.domain.model.DashboardSummary
import com.pamu.gymbro.domain.repository.DailyStatsRepository
import com.pamu.gymbro.domain.repository.DietRepository
import com.pamu.gymbro.domain.repository.WorkoutRepository
import com.pamu.gymbro.domain.repository.WorkoutSessionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onStart
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class GetDashboardSummaryUseCase @Inject constructor(
    private val workoutRepository: WorkoutRepository,
    private val dietRepository: DietRepository,
    private val statsRepository: DailyStatsRepository,
    private val sessionRepository: WorkoutSessionRepository
) {
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    operator fun invoke(): Flow<DashboardSummary> {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val dateKey = dateFormat.format(Date())

        return combine(
            workoutRepository.getAllWorkoutPlans().onStart { emit(emptyList()) },
            dietRepository.getAllDietPlans().onStart { emit(emptyList()) },
            statsRepository.getStatsForDate(dateKey).onStart { emit(null) },
            sessionRepository.getActiveSession().onStart { emit(null) }
        ) { workouts, diets, stats, session ->
            val activePlan = workouts.firstOrNull()
            if (activePlan != null) {
                workoutRepository.getWorkoutDaysForPlan(activePlan.id).onStart { emit(emptyList()) }.combine(
                    flowOf(diets to stats to session)
                ) { days, data ->
                    val (dietAndStats, currentSession) = data
                    val (d, s) = dietAndStats
                    
                    // In a real app we'd track which day the user is on.
                    // For now, if there's a session, use its day. Else use first day.
                    val targetDay = if (currentSession != null) {
                        days.find { it.id == currentSession.dayId } ?: days.firstOrNull()
                    } else {
                        days.firstOrNull()
                    }

                    val progress = when {
                        s?.workoutCompleted == true -> 100
                        currentSession != null && targetDay != null && targetDay.exercises.isNotEmpty() -> {
                            ((currentSession.currentExerciseIndex.toFloat() / targetDay.exercises.size.toFloat()) * 100).toInt()
                        }
                        else -> s?.workoutProgressPercentage ?: 0
                    }

                    DashboardSummary(
                        todayWorkout = if (targetDay != null) "${activePlan.name} - ${targetDay.title}" else activePlan.name,
                        workoutPlanId = activePlan.id,
                        workoutDayId = targetDay?.id,
                        workoutCompletionPercentage = progress,
                        currentWeightKg = 75.0f,
                        currentStreakDays = 3,
                        calorieGoal = d.firstOrNull()?.calories ?: 2500,
                        caloriesConsumed = 0,
                        caloriesBurned = s?.caloriesBurned ?: 0,
                        waterIntakeMl = s?.waterIntakeMl ?: 0,
                        waterGoalMl = 2500
                    )
                }
            } else {
                flowOf(
                    DashboardSummary(
                        todayWorkout = "No active plan",
                        workoutPlanId = null,
                        workoutDayId = null,
                        workoutCompletionPercentage = stats?.workoutProgressPercentage ?: 0,
                        currentWeightKg = 75.0f,
                        currentStreakDays = 0,
                        calorieGoal = diets.firstOrNull()?.calories ?: 2500,
                        caloriesConsumed = 0,
                        caloriesBurned = stats?.caloriesBurned ?: 0,
                        waterIntakeMl = stats?.waterIntakeMl ?: 0,
                        waterGoalMl = 2500
                    )
                )
            }
        }.flatMapLatest { it }
    }
}
