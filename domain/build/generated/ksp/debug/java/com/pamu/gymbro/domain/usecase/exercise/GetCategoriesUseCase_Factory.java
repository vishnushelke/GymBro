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
public final class GetCategoriesUseCase_Factory implements Factory<GetCategoriesUseCase> {
  private final Provider<ExerciseRepository> repositoryProvider;

  private GetCategoriesUseCase_Factory(Provider<ExerciseRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetCategoriesUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetCategoriesUseCase_Factory create(
      Provider<ExerciseRepository> repositoryProvider) {
    return new GetCategoriesUseCase_Factory(repositoryProvider);
  }

  public static GetCategoriesUseCase newInstance(ExerciseRepository repository) {
    return new GetCategoriesUseCase(repository);
  }
}
