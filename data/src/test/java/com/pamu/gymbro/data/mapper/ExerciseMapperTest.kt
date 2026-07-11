package com.pamu.gymbro.data.mapper

import com.pamu.gymbro.data.local.entity.ExerciseEntity
import com.pamu.gymbro.domain.model.Exercise
import org.junit.Assert.assertEquals
import org.junit.Test

class ExerciseMapperTest {

    @Test
    fun `ExerciseEntity toDomain maps correctly`() {
        // Given
        val entity = ExerciseEntity(
            id = 1,
            name = "Push up",
            categoryId = 1,
            difficulty = "Beginner",
            equipment = "None",
            primaryMuscle = "Chest",
            secondaryMuscle = "Triceps",
            description = "Desc",
            instructions = "Steps",
            tips = "Tips",
            commonMistakes = "Mistakes",
            imageUrl = "image",
            videoUrl = "video",
            caloriesBurned = 10
        )

        // When
        val domain = entity.toDomain()

        // Then
        assertEquals(entity.id, domain.id)
        assertEquals(entity.name, domain.name)
        assertEquals(entity.categoryId, domain.categoryId)
        assertEquals(entity.difficulty, domain.difficulty)
        assertEquals(entity.equipment, domain.equipment)
        assertEquals(entity.primaryMuscle, domain.primaryMuscle)
        assertEquals(entity.secondaryMuscle, domain.secondaryMuscle)
        assertEquals(entity.description, domain.description)
        assertEquals(entity.instructions, domain.instructions)
        assertEquals(entity.tips, domain.tips)
        assertEquals(entity.commonMistakes, domain.commonMistakes)
        assertEquals(entity.imageUrl, domain.imageUrl)
        assertEquals(entity.videoUrl, domain.videoUrl)
        assertEquals(entity.caloriesBurned, domain.caloriesBurned)
    }

    @Test
    fun `Exercise toEntity maps correctly`() {
        // Given
        val domain = Exercise(
            id = 1,
            name = "Push up",
            categoryId = 1,
            difficulty = "Beginner",
            equipment = "None",
            primaryMuscle = "Chest",
            secondaryMuscle = "Triceps",
            description = "Desc",
            instructions = "Steps",
            tips = "Tips",
            commonMistakes = "Mistakes",
            imageUrl = "image",
            videoUrl = "video",
            caloriesBurned = 10
        )

        // When
        val entity = domain.toEntity()

        // Then
        assertEquals(domain.id, entity.id)
        assertEquals(domain.name, entity.name)
        assertEquals(domain.categoryId, entity.categoryId)
        assertEquals(domain.difficulty, entity.difficulty)
        assertEquals(domain.equipment, entity.equipment)
        assertEquals(domain.primaryMuscle, entity.primaryMuscle)
        assertEquals(domain.secondaryMuscle, entity.secondaryMuscle)
        assertEquals(domain.description, entity.description)
        assertEquals(domain.instructions, entity.instructions)
        assertEquals(domain.tips, entity.tips)
        assertEquals(domain.commonMistakes, entity.commonMistakes)
        assertEquals(domain.imageUrl, entity.imageUrl)
        assertEquals(domain.videoUrl, entity.videoUrl)
        assertEquals(domain.caloriesBurned, entity.caloriesBurned)
    }
}
