package com.pamu.gymbro.domain.usecase.workout

import com.pamu.gymbro.domain.model.WorkoutPlan
import com.pamu.gymbro.domain.repository.WorkoutRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetWorkoutPlansUseCaseTest {

    private lateinit var getWorkoutPlansUseCase: GetWorkoutPlansUseCase
    private val repository: WorkoutRepository = mockk()

    @Before
    fun setUp() {
        getWorkoutPlansUseCase = GetWorkoutPlansUseCase(repository)
    }

    @Test
    fun `invoke returns all workout plans`() = runBlocking {
        // Given
        val plans = listOf(
            WorkoutPlan(1, "Full Body", "Beginner", "Maintenance", 4, "Desc"),
            WorkoutPlan(2, "PPL", "Intermediate", "Strength", 12, "Desc")
        )
        every { repository.getAllWorkoutPlans() } returns flowOf(plans)

        // When
        val result = getWorkoutPlansUseCase().first()

        // Then
        assertEquals(plans, result)
    }
}
