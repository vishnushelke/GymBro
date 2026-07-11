package com.pamu.gymbro.features.workout.presentation;

import com.pamu.gymbro.domain.usecase.workout.GetWorkoutDetailsUseCase;
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
public final class WorkoutDetailViewModel_Factory implements Factory<WorkoutDetailViewModel> {
  private final Provider<GetWorkoutDetailsUseCase> getWorkoutDetailsUseCaseProvider;

  private WorkoutDetailViewModel_Factory(
      Provider<GetWorkoutDetailsUseCase> getWorkoutDetailsUseCaseProvider) {
    this.getWorkoutDetailsUseCaseProvider = getWorkoutDetailsUseCaseProvider;
  }

  @Override
  public WorkoutDetailViewModel get() {
    return newInstance(getWorkoutDetailsUseCaseProvider.get());
  }

  public static WorkoutDetailViewModel_Factory create(
      Provider<GetWorkoutDetailsUseCase> getWorkoutDetailsUseCaseProvider) {
    return new WorkoutDetailViewModel_Factory(getWorkoutDetailsUseCaseProvider);
  }

  public static WorkoutDetailViewModel newInstance(
      GetWorkoutDetailsUseCase getWorkoutDetailsUseCase) {
    return new WorkoutDetailViewModel(getWorkoutDetailsUseCase);
  }
}
