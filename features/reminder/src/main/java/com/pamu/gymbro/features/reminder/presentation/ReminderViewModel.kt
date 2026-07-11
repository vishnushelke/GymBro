package com.pamu.gymbro.features.reminder.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.work.Data
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.pamu.gymbro.core.util.NotificationHelper
import com.pamu.gymbro.core.worker.ReminderWorker
import com.pamu.gymbro.domain.model.Reminder
import com.pamu.gymbro.domain.model.ReminderType
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Calendar
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class ReminderViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val workManager = WorkManager.getInstance(context)

    fun scheduleReminder(reminder: Reminder) {
        if (!reminder.isEnabled) {
            cancelReminder(reminder.type)
            return
        }

        val data = Data.Builder()
            .putString("title", "${reminder.type.name} Reminder")
            .putString("message", "It's time for your ${reminder.type.name.lowercase()}!")
            .putString("channelId", when (reminder.type) {
                ReminderType.WORKOUT -> NotificationHelper.WORKOUT_CHANNEL_ID
                ReminderType.MEAL -> NotificationHelper.MEAL_CHANNEL_ID
                ReminderType.WATER -> NotificationHelper.WATER_CHANNEL_ID
            })
            .build()

        val delay = calculateInitialDelay(reminder.hour, reminder.minute)

        val workRequest = PeriodicWorkRequestBuilder<ReminderWorker>(24, TimeUnit.HOURS)
            .setInitialDelay(delay, TimeUnit.MILLISECONDS)
            .setInputData(data)
            .build()

        workManager.enqueueUniquePeriodicWork(
            reminder.type.name,
            ExistingPeriodicWorkPolicy.UPDATE,
            workRequest
        )
    }

    private fun cancelReminder(type: ReminderType) {
        workManager.cancelUniqueWork(type.name)
    }

    private fun calculateInitialDelay(hour: Int, minute: Int): Long {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
        }

        if (calendar.timeInMillis <= System.currentTimeMillis()) {
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }

        return calendar.timeInMillis - System.currentTimeMillis()
    }
}
