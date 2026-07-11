package com.pamu.gymbro.core.worker;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.pamu.gymbro.core.util.NotificationHelper;
import dagger.internal.DaggerGenerated;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class ReminderWorker_Factory {
  private final Provider<NotificationHelper> notificationHelperProvider;

  private ReminderWorker_Factory(Provider<NotificationHelper> notificationHelperProvider) {
    this.notificationHelperProvider = notificationHelperProvider;
  }

  public ReminderWorker get(Context context, WorkerParameters workerParams) {
    return newInstance(context, workerParams, notificationHelperProvider.get());
  }

  public static ReminderWorker_Factory create(
      Provider<NotificationHelper> notificationHelperProvider) {
    return new ReminderWorker_Factory(notificationHelperProvider);
  }

  public static ReminderWorker newInstance(Context context, WorkerParameters workerParams,
      NotificationHelper notificationHelper) {
    return new ReminderWorker(context, workerParams, notificationHelper);
  }
}
