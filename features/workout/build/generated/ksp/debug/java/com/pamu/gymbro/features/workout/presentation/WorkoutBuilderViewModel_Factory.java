package com.pamu.gymbro.features.workout.presentation;

import com.pamu.gymbro.domain.usecase.exercise.GetExercisesUseCase;
import com.pamu.gymbro.domain.usecase.workout.GetWorkoutDetailsUseCase;
import com.pamu.gymbro.domain.usecase.workout.SaveWorkoutPlanUseCase;
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
public final class WorkoutBuilderViewModel_Factory implements Factory<WorkoutBuilderViewModel> {
  private final Provider<SaveWorkoutPlanUseCase> saveWorkoutPlanUseCaseProvider;

  private final Provider<GetExercisesUseCase> getExercisesUseCaseProvider;

  private final Provider<GetWorkoutDetailsUseCase> getWorkoutDetailsUseCaseProvider;

  private WorkoutBuilderViewModel_Factory(
      Provider<SaveWorkoutPlanUseCase> saveWorkoutPlanUseCaseProvider,
      Provider<GetExercisesUseCase> getExercisesUseCaseProvider,
      Provider<GetWorkoutDetailsUseCase> getWorkoutDetailsUseCaseProvider) {
    this.saveWorkoutPlanUseCaseProvider = saveWorkoutPlanUseCaseProvider;
    this.getExercisesUseCaseProvider = getExercisesUseCaseProvider;
    this.getWorkoutDetailsUseCaseProvider = getWorkoutDetailsUseCaseProvider;
  }

  @Override
  public WorkoutBuilderViewModel get() {
    return newInstance(saveWorkoutPlanUseCaseProvider.get(), getExercisesUseCaseProvider.get(), getWorkoutDetailsUseCaseProvider.get());
  }

  public static WorkoutBuilderViewModel_Factory create(
      Provider<SaveWorkoutPlanUseCase> saveWorkoutPlanUseCaseProvider,
      Provider<GetExercisesUseCase> getExercisesUseCaseProvider,
      Provider<GetWorkoutDetailsUseCase> getWorkoutDetailsUseCaseProvider) {
    return new WorkoutBuilderViewModel_Factory(saveWorkoutPlanUseCaseProvider, getExercisesUseCaseProvider, getWorkoutDetailsUseCaseProvider);
  }

  public static WorkoutBuilderViewModel newInstance(SaveWorkoutPlanUseCase saveWorkoutPlanUseCase,
      GetExercisesUseCase getExercisesUseCase, GetWorkoutDetailsUseCase getWorkoutDetailsUseCase) {
    return new WorkoutBuilderViewModel(saveWorkoutPlanUseCase, getExercisesUseCase, getWorkoutDetailsUseCase);
  }
}
