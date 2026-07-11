package com.pamu.gymbro.domain.model

enum class ReminderType {
    WORKOUT, MEAL, WATER
}

data class Reminder(
    val type: ReminderType,
    val isEnabled: Boolean,
    val hour: Int,
    val minute: Int
)
