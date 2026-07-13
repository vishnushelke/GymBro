package com.pamu.gymbro.features.workout.presentation;

import com.pamu.gymbro.domain.usecase.favorite.ToggleFavoriteUseCase;
import com.pamu.gymbro.domain.usecase.user.GetUserUseCase;
import com.pamu.gymbro.domain.usecase.workout.DeleteWorkoutPlanUseCase;
import com.pamu.gymbro.domain.usecase.workout.GetWorkoutPlansUseCase;
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
public final class WorkoutListViewModel_Factory implements Factory<WorkoutListViewModel> {
  private final Provider<GetWorkoutPlansUseCase> getWorkoutPlansUseCaseProvider;

  private final Provider<ToggleFavoriteUseCase> toggleFavoriteUseCaseProvider;

  private final Provider<GetUserUseCase> getUserUseCaseProvider;

  private final Provider<SaveWorkoutPlanUseCase> saveWorkoutPlanUseCaseProvider;

  private final Provider<DeleteWorkoutPlanUseCase> deleteWorkoutPlanUseCaseProvider;

  private WorkoutListViewModel_Factory(
      Provider<GetWorkoutPlansUseCase> getWorkoutPlansUseCaseProvider,
      Provider<ToggleFavoriteUseCase> toggleFavoriteUseCaseProvider,
      Provider<GetUserUseCase> getUserUseCaseProvider,
      Provider<SaveWorkoutPlanUseCase> saveWorkoutPlanUseCaseProvider,
      Provider<DeleteWorkoutPlanUseCase> deleteWorkoutPlanUseCaseProvider) {
    this.getWorkoutPlansUseCaseProvider = getWorkoutPlansUseCaseProvider;
    this.toggleFavoriteUseCaseProvider = toggleFavoriteUseCaseProvider;
    this.getUserUseCaseProvider = getUserUseCaseProvider;
    this.saveWorkoutPlanUseCaseProvider = saveWorkoutPlanUseCaseProvider;
    this.deleteWorkoutPlanUseCaseProvider = deleteWorkoutPlanUseCaseProvider;
  }

  @Override
  public WorkoutListViewModel get() {
    return newInstance(getWorkoutPlansUseCaseProvider.get(), toggleFavoriteUseCaseProvider.get(), getUserUseCaseProvider.get(), saveWorkoutPlanUseCaseProvider.get(), deleteWorkoutPlanUseCaseProvider.get());
  }

  public static WorkoutListViewModel_Factory create(
      Provider<GetWorkoutPlansUseCase> getWorkoutPlansUseCaseProvider,
      Provider<ToggleFavoriteUseCase> toggleFavoriteUseCaseProvider,
      Provider<GetUserUseCase> getUserUseCaseProvider,
      Provider<SaveWorkoutPlanUseCase> saveWorkoutPlanUseCaseProvider,
      Provider<DeleteWorkoutPlanUseCase> deleteWorkoutPlanUseCaseProvider) {
    return new WorkoutListViewModel_Factory(getWorkoutPlansUseCaseProvider, toggleFavoriteUseCaseProvider, getUserUseCaseProvider, saveWorkoutPlanUseCaseProvider, deleteWorkoutPlanUseCaseProvider);
  }

  public static WorkoutListViewModel newInstance(GetWorkoutPlansUseCase getWorkoutPlansUseCase,
      ToggleFavoriteUseCase toggleFavoriteUseCase, GetUserUseCase getUserUseCase,
      SaveWorkoutPlanUseCase saveWorkoutPlanUseCase,
      DeleteWorkoutPlanUseCase deleteWorkoutPlanUseCase) {
    return new WorkoutListViewModel(getWorkoutPlansUseCase, toggleFavoriteUseCase, getUserUseCase, saveWorkoutPlanUseCase, deleteWorkoutPlanUseCase);
  }
}
