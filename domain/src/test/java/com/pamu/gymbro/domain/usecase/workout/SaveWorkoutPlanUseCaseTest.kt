package com.pamu.gymbro.domain.usecase.workout

import com.pamu.gymbro.domain.model.WorkoutDay
import com.pamu.gymbro.domain.model.WorkoutPlan
import com.pamu.gymbro.domain.repository.WorkoutRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class SaveWorkoutPlanUseCaseTest {

    private lateinit var saveWorkoutPlanUseCase: SaveWorkoutPlanUseCase
    private val repository: WorkoutRepository = mockk()

    @Before
    fun setUp() {
        saveWorkoutPlanUseCase = SaveWorkoutPlanUseCase(repository)
    }

    @Test
    fun `invoke with valid plan saves it`() = runBlocking {
        // Given
        val plan = WorkoutPlan(0, "Test Plan", "Beginner", "Goal", 4, "Desc")
        val days = listOf(WorkoutDay(0, 0, 1, "Day 1"))
        coEvery { repository.insertWorkoutPlan(plan, days) } returns Unit

        // When
        val result = saveWorkoutPlanUseCase(plan, days)

        // Then
        assertTrue(result.isSuccess)
        coVerify { repository.insertWorkoutPlan(plan, days) }
    }

    @Test
    fun `invoke with empty name returns failure`() = runBlocking {
        // Given
        val plan = WorkoutPlan(0, "", "Beginner", "Goal", 4, "Desc")
        val days = listOf(WorkoutDay(0, 0, 1, "Day 1"))

        // When
        val result = saveWorkoutPlanUseCase(plan, days)

        // Then
        assertTrue(result.isFailure)
    }

    @Test
    fun `invoke with no days returns failure`() = runBlocking {
        // Given
        val plan = WorkoutPlan(0, "Test Plan", "Beginner", "Goal", 4, "Desc")
        val days = emptyList<WorkoutDay>()

        // When
        val result = saveWorkoutPlanUseCase(plan, days)

        // Then
        assertTrue(result.isFailure)
    }
}
