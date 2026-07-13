package com.pamu.gymbro.data.mapper

import com.pamu.gymbro.data.local.entity.UserProfileEntity
import com.pamu.gymbro.domain.model.User

fun UserProfileEntity.toDomain(): User {
    return User(
        name = name,
        isVegetarian = isVegetarian,
        level = experienceLevel,
        goal = fitnessGoal,
        isProfileCompleted = isProfileCompleted
    )
}

fun User.toEntity(): UserProfileEntity {
    return UserProfileEntity(
        id = 1L,
        name = name,
        isVegetarian = isVegetarian,
        experienceLevel = level,
        fitnessGoal = goal,
        isProfileCompleted = isProfileCompleted
    )
}
