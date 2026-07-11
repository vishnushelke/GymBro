package com.pamu.gymbro.core.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    companion object {
        const val WORKOUT_CHANNEL_ID = "workout_reminders"
        const val MEAL_CHANNEL_ID = "meal_reminders"
        const val WATER_CHANNEL_ID = "water_reminders"
    }

    init {
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channels = listOf(
                NotificationChannel(
                    WORKOUT_CHANNEL_ID,
                    "Workout Reminders",
                    NotificationManager.IMPORTANCE_DEFAULT
                ),
                NotificationChannel(
                    MEAL_CHANNEL_ID,
                    "Meal Reminders",
                    NotificationManager.IMPORTANCE_DEFAULT
                ),
                NotificationChannel(
                    WATER_CHANNEL_ID,
                    "Water Reminders",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            )
            notificationManager.createNotificationChannels(channels)
        }
    }

    fun showNotification(channelId: String, title: String, message: String) {
        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(System.currentTimeMillis().toInt(), notification)
    }
}
