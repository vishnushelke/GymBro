package com.pamu.gymbro.domain.usecase.exercise;

import com.pamu.gymbro.domain.repository.ExerciseRepository;
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
public final class GetExercisesUseCase_Factory implements Factory<GetExercisesUseCase> {
  private final Provider<ExerciseRepository> repositoryProvider;

  private GetExercisesUseCase_Factory(Provider<ExerciseRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetExercisesUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetExercisesUseCase_Factory create(
      Provider<ExerciseRepository> repositoryProvider) {
    return new GetExercisesUseCase_Factory(repositoryProvider);
  }

  public static GetExercisesUseCase newInstance(ExerciseRepository repository) {
    return new GetExercisesUseCase(repository);
  }
}
