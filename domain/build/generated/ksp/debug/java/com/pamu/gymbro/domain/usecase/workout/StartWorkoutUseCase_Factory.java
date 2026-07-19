package com.pamu.gymbro.domain.usecase.workout;

import com.pamu.gymbro.domain.repository.WorkoutSessionRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class StartWorkoutUseCase_Factory implements Factory<StartWorkoutUseCase> {
  private final Provider<WorkoutSessionRepository> repositoryProvider;

  private StartWorkoutUseCase_Factory(Provider<WorkoutSessionRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public StartWorkoutUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static StartWorkoutUseCase_Factory create(
      Provider<WorkoutSessionRepository> repositoryProvider) {
    return new StartWorkoutUseCase_Factory(repositoryProvider);
  }

  public static StartWorkoutUseCase newInstance(WorkoutSessionRepository repository) {
    return new StartWorkoutUseCase(repository);
  }
}
