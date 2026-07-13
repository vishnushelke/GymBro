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
public final class DeleteWorkoutPlanUseCase_Factory implements Factory<DeleteWorkoutPlanUseCase> {
  private final Provider<WorkoutRepository> repositoryProvider;

  private DeleteWorkoutPlanUseCase_Factory(Provider<WorkoutRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public DeleteWorkoutPlanUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static DeleteWorkoutPlanUseCase_Factory create(
      Provider<WorkoutRepository> repositoryProvider) {
    return new DeleteWorkoutPlanUseCase_Factory(repositoryProvider);
  }

  public static DeleteWorkoutPlanUseCase newInstance(WorkoutRepository repository) {
    return new DeleteWorkoutPlanUseCase(repository);
  }
}
