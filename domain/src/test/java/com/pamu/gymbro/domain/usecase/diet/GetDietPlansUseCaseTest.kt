package com.pamu.gymbro.domain.usecase.diet

import com.pamu.gymbro.domain.model.DietPlan
import com.pamu.gymbro.domain.repository.DietRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetDietPlansUseCaseTest {

    private lateinit var getDietPlansUseCase: GetDietPlansUseCase
    private val repository: DietRepository = mockk()

    @Before
    fun setUp() {
        getDietPlansUseCase = GetDietPlansUseCase(repository)
    }

    @Test
    fun `invoke returns all diet plans`() = runBlocking {
        // Given
        val plans = listOf(
            DietPlan(1, "Weight Loss", "Lose Fat", 2000, 150, 200, 50),
            DietPlan(2, "Muscle Gain", "Build Muscle", 3000, 200, 350, 80)
        )
        every { repository.getAllDietPlans() } returns flowOf(plans)

        // When
        val result = getDietPlansUseCase().first()

        // Then
        assertEquals(plans, result)
    }
}
