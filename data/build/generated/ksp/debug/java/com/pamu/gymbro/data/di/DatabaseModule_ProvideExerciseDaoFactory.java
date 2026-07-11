package com.pamu.gymbro.data.di;

import com.pamu.gymbro.data.local.GymBroDatabase;
import com.pamu.gymbro.data.local.dao.ExerciseDao;
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
public final class DatabaseModule_ProvideExerciseDaoFactory implements Factory<ExerciseDao> {
  private final Provider<GymBroDatabase> dbProvider;

  private DatabaseModule_ProvideExerciseDaoFactory(Provider<GymBroDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public ExerciseDao get() {
    return provideExerciseDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideExerciseDaoFactory create(
      Provider<GymBroDatabase> dbProvider) {
    return new DatabaseModule_ProvideExerciseDaoFactory(dbProvider);
  }

  public static ExerciseDao provideExerciseDao(GymBroDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideExerciseDao(db));
  }
}
