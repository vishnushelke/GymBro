package com.pamu.gymbro.domain.usecase.exercise

import com.pamu.gymbro.domain.model.Exercise
import com.pamu.gymbro.domain.repository.ExerciseRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetExercisesUseCaseTest {

    private lateinit var getExercisesUseCase: GetExercisesUseCase
    private val repository: ExerciseRepository = mockk()

    @Before
    fun setUp() {
        getExercisesUseCase = GetExercisesUseCase(repository)
    }

    @Test
    fun `invoke with null categoryId returns all exercises`() = runBlocking {
        // Given
        val exercises = listOf(
            Exercise(1, "Push up", 1, "Beginner", "None", "Chest", "", "", "", "", "", "", "", 10),
            Exercise(2, "Squat", 2, "Beginner", "None", "Legs", "", "", "", "", "", "", "", 15)
        )
        every { repository.getAllExercises() } returns flowOf(exercises)

        // When
        val result = getExercisesUseCase(null).first()

        // Then
        assertEquals(exercises, result)
    }

    @Test
    fun `invoke with categoryId returns exercises by category`() = runBlocking {
        // Given
        val categoryId = 1L
        val exercises = listOf(
            Exercise(1, "Push up", 1, "Beginner", "None", "Chest", "", "", "", "", "", "", "", 10)
        )
        every { repository.getExercisesByCategory(categoryId) } returns flowOf(exercises)

        // When
        val result = getExercisesUseCase(categoryId).first()

        // Then
        assertEquals(exercises, result)
    }
}
