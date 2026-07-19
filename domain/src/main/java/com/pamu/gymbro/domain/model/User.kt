package com.pamu.gymbro.domain.model

data class User(
    val name: String,
    val isVegetarian: Boolean,
    val level: String, // Beginner, Intermediate, Expert
    val goal: String, // Weight Loss, Weight Gain, Maintenance
    val email: String = "",
    val phone: String = "",
    val age: Int = 0,
    val sex: String = "",
    val unitPreference: String = "KG", // KG, LBS
    val isProfileCompleted: Boolean = false
)
