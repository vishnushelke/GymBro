package com.pamu.gymbro.features.workout.presentation;

import com.pamu.gymbro.domain.usecase.favorite.ToggleFavoriteUseCase;
import com.pamu.gymbro.domain.usecase.workout.GetWorkoutPlansUseCase;
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
public final class WorkoutListViewModel_Factory implements Factory<WorkoutListViewModel> {
  private final Provider<GetWorkoutPlansUseCase> getWorkoutPlansUseCaseProvider;

  private final Provider<ToggleFavoriteUseCase> toggleFavoriteUseCaseProvider;

  private WorkoutListViewModel_Factory(
      Provider<GetWorkoutPlansUseCase> getWorkoutPlansUseCaseProvider,
      Provider<ToggleFavoriteUseCase> toggleFavoriteUseCaseProvider) {
    this.getWorkoutPlansUseCaseProvider = getWorkoutPlansUseCaseProvider;
    this.toggleFavoriteUseCaseProvider = toggleFavoriteUseCaseProvider;
  }

  @Override
  public WorkoutListViewModel get() {
    return newInstance(getWorkoutPlansUseCaseProvider.get(), toggleFavoriteUseCaseProvider.get());
  }

  public static WorkoutListViewModel_Factory create(
      Provider<GetWorkoutPlansUseCase> getWorkoutPlansUseCaseProvider,
      Provider<ToggleFavoriteUseCase> toggleFavoriteUseCaseProvider) {
    return new WorkoutListViewModel_Factory(getWorkoutPlansUseCaseProvider, toggleFavoriteUseCaseProvider);
  }

  public static WorkoutListViewModel newInstance(GetWorkoutPlansUseCase getWorkoutPlansUseCase,
      ToggleFavoriteUseCase toggleFavoriteUseCase) {
    return new WorkoutListViewModel(getWorkoutPlansUseCase, toggleFavoriteUseCase);
  }
}
