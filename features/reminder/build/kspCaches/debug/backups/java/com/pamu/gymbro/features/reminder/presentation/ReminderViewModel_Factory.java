package com.pamu.gymbro.features.reminder.presentation;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class ReminderViewModel_Factory implements Factory<ReminderViewModel> {
  private final Provider<Context> contextProvider;

  private ReminderViewModel_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public ReminderViewModel get() {
    return newInstance(contextProvider.get());
  }

  public static ReminderViewModel_Factory create(Provider<Context> contextProvider) {
    return new ReminderViewModel_Factory(contextProvider);
  }

  public static ReminderViewModel newInstance(Context context) {
    return new ReminderViewModel(context);
  }
}
