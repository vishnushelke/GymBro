package com.pamu.gymbro.features.reminder.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pamu.gymbro.domain.model.Reminder
import com.pamu.gymbro.domain.model.ReminderType

@Composable
fun ReminderSettingsScreen(
    viewModel: ReminderViewModel = hiltViewModel()
) {
    var workoutEnabled by remember { mutableStateOf(false) }
    var mealEnabled by remember { mutableStateOf(false) }
    var waterEnabled by remember { mutableStateOf(false) }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "Reminder Settings",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            ReminderToggle(
                label = "Workout Reminders",
                isEnabled = workoutEnabled,
                onToggle = {
                    workoutEnabled = it
                    viewModel.scheduleReminder(Reminder(ReminderType.WORKOUT, it, 8, 0))
                }
            )

            ReminderToggle(
                label = "Meal Reminders",
                isEnabled = mealEnabled,
                onToggle = {
                    mealEnabled = it
                    viewModel.scheduleReminder(Reminder(ReminderType.MEAL, it, 12, 0))
                }
            )

            ReminderToggle(
                label = "Water Reminders",
                isEnabled = waterEnabled,
                onToggle = {
                    waterEnabled = it
                    viewModel.scheduleReminder(Reminder(ReminderType.WATER, it, 10, 0))
                }
            )
        }
    }
}

@Composable
fun ReminderToggle(
    label: String,
    isEnabled: Boolean,
    onToggle: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
        Switch(checked = isEnabled, onCheckedChange = onToggle)
    }
}
