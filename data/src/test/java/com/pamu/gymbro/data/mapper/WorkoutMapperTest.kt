package com.pamu.gymbro.data.mapper

import com.pamu.gymbro.data.local.entity.WorkoutPlanEntity
import com.pamu.gymbro.domain.model.WorkoutPlan
import org.junit.Assert.assertEquals
import org.junit.Test

class WorkoutMapperTest {

    @Test
    fun `WorkoutPlanEntity toDomain maps correctly`() {
        // Given
        val entity = WorkoutPlanEntity(
            id = 1,
            name = "Plan",
            level = "Beginner",
            goal = "Goal",
            durationWeeks = 4,
            description = "Desc"
        )

        // When
        val domain = entity.toDomain()

        // Then
        assertEquals(entity.id, domain.id)
        assertEquals(entity.name, domain.name)
        assertEquals(entity.level, domain.level)
        assertEquals(entity.goal, domain.goal)
        assertEquals(entity.durationWeeks, domain.durationWeeks)
        assertEquals(entity.description, domain.description)
    }

    @Test
    fun `WorkoutPlan toEntity maps correctly`() {
        // Given
        val domain = WorkoutPlan(
            id = 1,
            name = "Plan",
            level = "Beginner",
            goal = "Goal",
            durationWeeks = 4,
            description = "Desc"
        )

        // When
        val entity = domain.toEntity()

        // Then
        assertEquals(domain.id, entity.id)
        assertEquals(domain.name, entity.name)
        assertEquals(domain.level, entity.level)
        assertEquals(domain.goal, entity.goal)
        assertEquals(domain.durationWeeks, entity.durationWeeks)
        assertEquals(domain.description, entity.description)
    }
}
