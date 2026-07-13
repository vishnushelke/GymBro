package com.pamu.gymbro.features.workout.presentation;

import com.pamu.gymbro.domain.usecase.workout.GetWorkoutDayUseCase;
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
public final class WorkoutDayDetailViewModel_Factory implements Factory<WorkoutDayDetailViewModel> {
  private final Provider<GetWorkoutDayUseCase> getWorkoutDayUseCaseProvider;

  private WorkoutDayDetailViewModel_Factory(
      Provider<GetWorkoutDayUseCase> getWorkoutDayUseCaseProvider) {
    this.getWorkoutDayUseCaseProvider = getWorkoutDayUseCaseProvider;
  }

  @Override
  public WorkoutDayDetailViewModel get() {
    return newInstance(getWorkoutDayUseCaseProvider.get());
  }

  public static WorkoutDayDetailViewModel_Factory create(
      Provider<GetWorkoutDayUseCase> getWorkoutDayUseCaseProvider) {
    return new WorkoutDayDetailViewModel_Factory(getWorkoutDayUseCaseProvider);
  }

  public static WorkoutDayDetailViewModel newInstance(GetWorkoutDayUseCase getWorkoutDayUseCase) {
    return new WorkoutDayDetailViewModel(getWorkoutDayUseCase);
  }
}
