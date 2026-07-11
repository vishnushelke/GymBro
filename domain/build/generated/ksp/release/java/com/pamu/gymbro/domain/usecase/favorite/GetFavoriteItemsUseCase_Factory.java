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
public final class GetFavoriteItemsUseCase_Factory implements Factory<GetFavoriteItemsUseCase> {
  private final Provider<ExerciseRepository> exerciseRepositoryProvider;

  private final Provider<WorkoutRepository> workoutRepositoryProvider;

  private final Provider<DietRepository> dietRepositoryProvider;

  private GetFavoriteItemsUseCase_Factory(Provider<ExerciseRepository> exerciseRepositoryProvider,
      Provider<WorkoutRepository> workoutRepositoryProvider,
      Provider<DietRepository> dietRepositoryProvider) {
    this.exerciseRepositoryProvider = exerciseRepositoryProvider;
    this.workoutRepositoryProvider = workoutRepositoryProvider;
    this.dietRepositoryProvider = dietRepositoryProvider;
  }

  @Override
  public GetFavoriteItemsUseCase get() {
    return newInstance(exerciseRepositoryProvider.get(), workoutRepositoryProvider.get(), dietRepositoryProvider.get());
  }

  public static GetFavoriteItemsUseCase_Factory create(
      Provider<ExerciseRepository> exerciseRepositoryProvider,
      Provider<WorkoutRepository> workoutRepositoryProvider,
      Provider<DietRepository> dietRepositoryProvider) {
    return new GetFavoriteItemsUseCase_Factory(exerciseRepositoryProvider, workoutRepositoryProvider, dietRepositoryProvider);
  }

  public static GetFavoriteItemsUseCase newInstance(ExerciseRepository exerciseRepository,
      WorkoutRepository workoutRepository, DietRepository dietRepository) {
    return new GetFavoriteItemsUseCase(exerciseRepository, workoutRepository, dietRepository);
  }
}
