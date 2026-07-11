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
public final class GetExerciseByIdUseCase_Factory implements Factory<GetExerciseByIdUseCase> {
  private final Provider<ExerciseRepository> repositoryProvider;

  private GetExerciseByIdUseCase_Factory(Provider<ExerciseRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetExerciseByIdUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetExerciseByIdUseCase_Factory create(
      Provider<ExerciseRepository> repositoryProvider) {
    return new GetExerciseByIdUseCase_Factory(repositoryProvider);
  }

  public static GetExerciseByIdUseCase newInstance(ExerciseRepository repository) {
    return new GetExerciseByIdUseCase(repository);
  }
}
