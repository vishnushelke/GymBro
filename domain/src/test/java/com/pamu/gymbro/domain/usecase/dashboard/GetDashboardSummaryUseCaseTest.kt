package com.pamu.gymbro.domain.usecase.dashboard

import com.pamu.gymbro.domain.model.DietPlan
import com.pamu.gymbro.domain.model.WorkoutPlan
import com.pamu.gymbro.domain.repository.DietRepository
import com.pamu.gymbro.domain.repository.WorkoutRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetDashboardSummaryUseCaseTest {

    private lateinit var getDashboardSummaryUseCase: GetDashboardSummaryUseCase
    private val workoutRepository: WorkoutRepository = mockk()
    private val dietRepository: DietRepository = mockk()

    @Before
    fun setUp() {
        getDashboardSummaryUseCase = GetDashboardSummaryUseCase(workoutRepository, dietRepository)
    }

    @Test
    fun `invoke returns aggregated summary from repositories`() = runBlocking {
        // Given
        val workouts = listOf(WorkoutPlan(1, "Full Body", "Beginner", "Goal", 4, "Desc"))
        val diets = listOf(DietPlan(1, "Weight Loss", "Lose Fat", 2000, 150, 200, 50))
        
        every { workoutRepository.getAllWorkoutPlans() } returns flowOf(workouts)
        every { dietRepository.getAllDietPlans() } returns flowOf(diets)

        // When
        val result = getDashboardSummaryUseCase().first()

        // Then
        assertEquals("Full Body", result.todayWorkout)
        assertEquals(2000, result.calorieGoal)
    }
}
