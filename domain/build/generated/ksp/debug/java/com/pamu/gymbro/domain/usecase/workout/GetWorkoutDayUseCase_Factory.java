package com.pamu.gymbro.domain.usecase.workout;

import com.pamu.gymbro.domain.repository.WorkoutRepository;
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
public final class GetWorkoutDayUseCase_Factory implements Factory<GetWorkoutDayUseCase> {
  private final Provider<WorkoutRepository> repositoryProvider;

  private GetWorkoutDayUseCase_Factory(Provider<WorkoutRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetWorkoutDayUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetWorkoutDayUseCase_Factory create(
      Provider<WorkoutRepository> repositoryProvider) {
    return new GetWorkoutDayUseCase_Factory(repositoryProvider);
  }

  public static GetWorkoutDayUseCase newInstance(WorkoutRepository repository) {
    return new GetWorkoutDayUseCase(repository);
  }
}
