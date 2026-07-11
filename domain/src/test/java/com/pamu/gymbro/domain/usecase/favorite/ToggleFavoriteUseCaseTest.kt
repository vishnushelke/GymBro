package com.pamu.gymbro.domain.usecase.favorite

import com.pamu.gymbro.domain.repository.DietRepository
import com.pamu.gymbro.domain.repository.ExerciseRepository
import com.pamu.gymbro.domain.repository.WorkoutRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ToggleFavoriteUseCaseTest {

    private lateinit var toggleFavoriteUseCase: ToggleFavoriteUseCase
    private val exerciseRepository: ExerciseRepository = mockk()
    private val workoutRepository: WorkoutRepository = mockk()
    private val dietRepository: DietRepository = mockk()

    @Before
    fun setUp() {
        toggleFavoriteUseCase = ToggleFavoriteUseCase(exerciseRepository, workoutRepository, dietRepository)
    }

    @Test
    fun `invoke with EXERCISE type calls exercise repository`() = runBlocking {
        // Given
        val id = 1L
        val isFavorite = true
        coEvery { exerciseRepository.updateFavoriteStatus(id, isFavorite) } returns Unit

        // When
        toggleFavoriteUseCase(id, FavoriteType.EXERCISE, isFavorite)

        // Then
        coVerify { exerciseRepository.updateFavoriteStatus(id, isFavorite) }
    }

    @Test
    fun `invoke with WORKOUT type calls workout repository`() = runBlocking {
        // Given
        val id = 1L
        val isFavorite = true
        coEvery { workoutRepository.updateFavoriteStatus(id, isFavorite) } returns Unit

        // When
        toggleFavoriteUseCase(id, FavoriteType.WORKOUT, isFavorite)

        // Then
        coVerify { workoutRepository.updateFavoriteStatus(id, isFavorite) }
    }

    @Test
    fun `invoke with DIET type calls diet repository`() = runBlocking {
        // Given
        val id = 1L
        val isFavorite = true
        coEvery { dietRepository.updateFavoriteStatus(id, isFavorite) } returns Unit

        // When
        toggleFavoriteUseCase(id, FavoriteType.DIET, isFavorite)

        // Then
        coVerify { dietRepository.updateFavoriteStatus(id, isFavorite) }
    }
}
