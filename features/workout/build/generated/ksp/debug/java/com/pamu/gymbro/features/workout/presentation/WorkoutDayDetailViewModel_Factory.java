package com.pamu.gymbro.features.workout.presentation;

import com.pamu.gymbro.domain.usecase.user.GetUserUseCase;
import com.pamu.gymbro.domain.usecase.workout.GetWorkoutDayUseCase;
import com.pamu.gymbro.domain.usecase.workout.UpdateWorkoutExerciseWeightUseCase;
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

  private final Provider<UpdateWorkoutExerciseWeightUseCase> updateWorkoutExerciseWeightUseCaseProvider;

  private final Provider<GetUserUseCase> getUserUseCaseProvider;

  private WorkoutDayDetailViewModel_Factory(
      Provider<GetWorkoutDayUseCase> getWorkoutDayUseCaseProvider,
      Provider<UpdateWorkoutExerciseWeightUseCase> updateWorkoutExerciseWeightUseCaseProvider,
      Provider<GetUserUseCase> getUserUseCaseProvider) {
    this.getWorkoutDayUseCaseProvider = getWorkoutDayUseCaseProvider;
    this.updateWorkoutExerciseWeightUseCaseProvider = updateWorkoutExerciseWeightUseCaseProvider;
    this.getUserUseCaseProvider = getUserUseCaseProvider;
  }

  @Override
  public WorkoutDayDetailViewModel get() {
    return newInstance(getWorkoutDayUseCaseProvider.get(), updateWorkoutExerciseWeightUseCaseProvider.get(), getUserUseCaseProvider.get());
  }

  public static WorkoutDayDetailViewModel_Factory create(
      Provider<GetWorkoutDayUseCase> getWorkoutDayUseCaseProvider,
      Provider<UpdateWorkoutExerciseWeightUseCase> updateWorkoutExerciseWeightUseCaseProvider,
      Provider<GetUserUseCase> getUserUseCaseProvider) {
    return new WorkoutDayDetailViewModel_Factory(getWorkoutDayUseCaseProvider, updateWorkoutExerciseWeightUseCaseProvider, getUserUseCaseProvider);
  }

  public static WorkoutDayDetailViewModel newInstance(GetWorkoutDayUseCase getWorkoutDayUseCase,
      UpdateWorkoutExerciseWeightUseCase updateWorkoutExerciseWeightUseCase,
      GetUserUseCase getUserUseCase) {
    return new WorkoutDayDetailViewModel(getWorkoutDayUseCase, updateWorkoutExerciseWeightUseCase, getUserUseCase);
  }
}
