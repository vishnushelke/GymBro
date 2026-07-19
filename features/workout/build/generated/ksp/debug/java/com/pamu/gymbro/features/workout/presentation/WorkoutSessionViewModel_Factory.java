package com.pamu.gymbro.features.workout.presentation;

import com.pamu.gymbro.domain.usecase.workout.CompleteSetUseCase;
import com.pamu.gymbro.domain.usecase.workout.FinishWorkoutUseCase;
import com.pamu.gymbro.domain.usecase.workout.GetActiveWorkoutSessionUseCase;
import com.pamu.gymbro.domain.usecase.workout.GetWorkoutDayUseCase;
import com.pamu.gymbro.domain.usecase.workout.StartWorkoutUseCase;
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
public final class WorkoutSessionViewModel_Factory implements Factory<WorkoutSessionViewModel> {
  private final Provider<GetActiveWorkoutSessionUseCase> getActiveWorkoutSessionUseCaseProvider;

  private final Provider<StartWorkoutUseCase> startWorkoutUseCaseProvider;

  private final Provider<CompleteSetUseCase> completeSetUseCaseProvider;

  private final Provider<FinishWorkoutUseCase> finishWorkoutUseCaseProvider;

  private final Provider<GetWorkoutDayUseCase> getWorkoutDayUseCaseProvider;

  private WorkoutSessionViewModel_Factory(
      Provider<GetActiveWorkoutSessionUseCase> getActiveWorkoutSessionUseCaseProvider,
      Provider<StartWorkoutUseCase> startWorkoutUseCaseProvider,
      Provider<CompleteSetUseCase> completeSetUseCaseProvider,
      Provider<FinishWorkoutUseCase> finishWorkoutUseCaseProvider,
      Provider<GetWorkoutDayUseCase> getWorkoutDayUseCaseProvider) {
    this.getActiveWorkoutSessionUseCaseProvider = getActiveWorkoutSessionUseCaseProvider;
    this.startWorkoutUseCaseProvider = startWorkoutUseCaseProvider;
    this.completeSetUseCaseProvider = completeSetUseCaseProvider;
    this.finishWorkoutUseCaseProvider = finishWorkoutUseCaseProvider;
    this.getWorkoutDayUseCaseProvider = getWorkoutDayUseCaseProvider;
  }

  @Override
  public WorkoutSessionViewModel get() {
    return newInstance(getActiveWorkoutSessionUseCaseProvider.get(), startWorkoutUseCaseProvider.get(), completeSetUseCaseProvider.get(), finishWorkoutUseCaseProvider.get(), getWorkoutDayUseCaseProvider.get());
  }

  public static WorkoutSessionViewModel_Factory create(
      Provider<GetActiveWorkoutSessionUseCase> getActiveWorkoutSessionUseCaseProvider,
      Provider<StartWorkoutUseCase> startWorkoutUseCaseProvider,
      Provider<CompleteSetUseCase> completeSetUseCaseProvider,
      Provider<FinishWorkoutUseCase> finishWorkoutUseCaseProvider,
      Provider<GetWorkoutDayUseCase> getWorkoutDayUseCaseProvider) {
    return new WorkoutSessionViewModel_Factory(getActiveWorkoutSessionUseCaseProvider, startWorkoutUseCaseProvider, completeSetUseCaseProvider, finishWorkoutUseCaseProvider, getWorkoutDayUseCaseProvider);
  }

  public static WorkoutSessionViewModel newInstance(
      GetActiveWorkoutSessionUseCase getActiveWorkoutSessionUseCase,
      StartWorkoutUseCase startWorkoutUseCase, CompleteSetUseCase completeSetUseCase,
      FinishWorkoutUseCase finishWorkoutUseCase, GetWorkoutDayUseCase getWorkoutDayUseCase) {
    return new WorkoutSessionViewModel(getActiveWorkoutSessionUseCase, startWorkoutUseCase, completeSetUseCase, finishWorkoutUseCase, getWorkoutDayUseCase);
  }
}
