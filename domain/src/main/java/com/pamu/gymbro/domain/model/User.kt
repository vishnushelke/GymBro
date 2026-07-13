package com.pamu.gymbro.domain.model

data class User(
    val name: String,
    val isVegetarian: Boolean,
    val level: String, // Beginner, Intermediate, Expert
    val goal: String, // Weight Loss, Weight Gain, Maintenance
    val isProfileCompleted: Boolean = false
)
