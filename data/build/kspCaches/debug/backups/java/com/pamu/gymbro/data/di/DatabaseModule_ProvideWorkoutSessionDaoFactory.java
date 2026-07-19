package com.pamu.gymbro.data.di;

import com.pamu.gymbro.data.local.GymBroDatabase;
import com.pamu.gymbro.data.local.dao.WorkoutSessionDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class DatabaseModule_ProvideWorkoutSessionDaoFactory implements Factory<WorkoutSessionDao> {
  private final Provider<GymBroDatabase> dbProvider;

  private DatabaseModule_ProvideWorkoutSessionDaoFactory(Provider<GymBroDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public WorkoutSessionDao get() {
    return provideWorkoutSessionDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideWorkoutSessionDaoFactory create(
      Provider<GymBroDatabase> dbProvider) {
    return new DatabaseModule_ProvideWorkoutSessionDaoFactory(dbProvider);
  }

  public static WorkoutSessionDao provideWorkoutSessionDao(GymBroDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideWorkoutSessionDao(db));
  }
}
