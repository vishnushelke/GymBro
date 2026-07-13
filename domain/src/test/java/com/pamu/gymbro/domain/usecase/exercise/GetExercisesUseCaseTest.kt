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

    private fun createExercise(id: Long, name: String, primaryMuscle: String = "") = Exercise(
        id = id,
        name = name,
        categoryId = 1L,
        difficulty = "Beginner",
        equipment = "None",
        primaryMuscle = primaryMuscle,
        secondaryMuscles = "",
        exerciseType = "",
        movementPattern = "",
        caloriesBurnedEstimate = 10,
        description = "",
        benefits = "",
        commonMistakes = "",
        safetyWarnings = "",
        beginnerVariation = "",
        intermediateVariation = "",
        advancedVariation = "",
        instructions = "",
        thumbnailUrl = "",
        videoFrontUrl = "",
        videoSideUrl = "",
        videoDuration = 0,
        videoFps = 0,
        videoResolution = ""
    )

    @Test
    fun `invoke with null categoryId returns all exercises`() = runBlocking {
        // Given
        val exercises = listOf(
            createExercise(1, "Push up"),
            createExercise(2, "Squat")
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
            createExercise(1, "Push up")
        )
        every { repository.getExercisesByCategory(categoryId) } returns flowOf(exercises)

        // When
        val result = getExercisesUseCase(categoryId).first()

        // Then
        assertEquals(exercises, result)
    }

    @Test
    fun `invoke with query filters exercises by name`() = runBlocking {
        // Given
        val exercises = listOf(
            createExercise(1, "Push up"),
            createExercise(2, "Squat")
        )
        every { repository.getAllExercises() } returns flowOf(exercises)

        // When
        val result = getExercisesUseCase(null, "push").first()

        // Then
        assertEquals(1, result.size)
        assertEquals("Push up", result[0].name)
    }

    @Test
    fun `invoke with query filters exercises by primary muscle`() = runBlocking {
        // Given
        val exercises = listOf(
            createExercise(1, "Push up", "Chest"),
            createExercise(2, "Squat", "Legs")
        )
        every { repository.getAllExercises() } returns flowOf(exercises)

        // When
        val result = getExercisesUseCase(null, "chest").first()

        // Then
        assertEquals(1, result.size)
        assertEquals("Chest", result[0].primaryMuscle)
    }
}
