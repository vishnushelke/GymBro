package com.pamu.gymbro.domain.usecase.favorite;

import com.pamu.gymbro.domain.repository.DietRepository;
import com.pamu.gymbro.domain.repository.ExerciseRepository;
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
public final class ToggleFavoriteUseCase_Factory implements Factory<ToggleFavoriteUseCase> {
  private final Provider<ExerciseRepository> exerciseRepositoryProvider;

  private final Provider<WorkoutRepository> workoutRepositoryProvider;

  private final Provider<DietRepository> dietRepositoryProvider;

  private ToggleFavoriteUseCase_Factory(Provider<ExerciseRepository> exerciseRepositoryProvider,
      Provider<WorkoutRepository> workoutRepositoryProvider,
      Provider<DietRepository> dietRepositoryProvider) {
    this.exerciseRepositoryProvider = exerciseRepositoryProvider;
    this.workoutRepositoryProvider = workoutRepositoryProvider;
    this.dietRepositoryProvider = dietRepositoryProvider;
  }

  @Override
  public ToggleFavoriteUseCase get() {
    return newInstance(exerciseRepositoryProvider.get(), workoutRepositoryProvider.get(), dietRepositoryProvider.get());
  }

  public static ToggleFavoriteUseCase_Factory create(
      Provider<ExerciseRepository> exerciseRepositoryProvider,
      Provider<WorkoutRepository> workoutRepositoryProvider,
      Provider<DietRepository> dietRepositoryProvider) {
    return new ToggleFavoriteUseCase_Factory(exerciseRepositoryProvider, workoutRepositoryProvider, dietRepositoryProvider);
  }

  public static ToggleFavoriteUseCase newInstance(ExerciseRepository exerciseRepository,
      WorkoutRepository workoutRepository, DietRepository dietRepository) {
    return new ToggleFavoriteUseCase(exerciseRepository, workoutRepository, dietRepository);
  }
}
