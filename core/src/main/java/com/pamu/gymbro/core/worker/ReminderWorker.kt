package com.pamu.gymbro.core.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.pamu.gymbro.core.util.NotificationHelper
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class ReminderWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val notificationHelper: NotificationHelper
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val title = inputData.getString("title") ?: "GymBro Reminder"
        val message = inputData.getString("message") ?: "Time for your activity!"
        val channelId = inputData.getString("channelId") ?: NotificationHelper.WORKOUT_CHANNEL_ID

        notificationHelper.showNotification(channelId, title, message)

        return Result.success()
    }
}
