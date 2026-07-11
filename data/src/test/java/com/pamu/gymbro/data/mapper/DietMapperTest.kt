package com.pamu.gymbro.data.mapper

import com.pamu.gymbro.data.local.entity.DietPlanEntity
import com.pamu.gymbro.domain.model.DietPlan
import org.junit.Assert.assertEquals
import org.junit.Test

class DietMapperTest {

    @Test
    fun `DietPlanEntity toDomain maps correctly`() {
        // Given
        val entity = DietPlanEntity(
            id = 1,
            name = "Plan",
            goal = "Goal",
            calories = 2000,
            protein = 150,
            carbs = 200,
            fats = 50
        )

        // When
        val domain = entity.toDomain()

        // Then
        assertEquals(entity.id, domain.id)
        assertEquals(entity.name, domain.name)
        assertEquals(entity.goal, domain.goal)
        assertEquals(entity.calories, domain.calories)
        assertEquals(entity.protein, domain.protein)
        assertEquals(entity.carbs, domain.carbs)
        assertEquals(entity.fats, domain.fats)
    }

    @Test
    fun `DietPlan toEntity maps correctly`() {
        // Given
        val domain = DietPlan(
            id = 1,
            name = "Plan",
            goal = "Goal",
            calories = 2000,
            protein = 150,
            carbs = 200,
            fats = 50
        )

        // When
        val entity = domain.toEntity()

        // Then
        assertEquals(domain.id, entity.id)
        assertEquals(domain.name, entity.name)
        assertEquals(domain.goal, entity.goal)
        assertEquals(domain.calories, entity.calories)
        assertEquals(domain.protein, entity.protein)
        assertEquals(domain.carbs, entity.carbs)
        assertEquals(domain.fats, entity.fats)
    }
}
