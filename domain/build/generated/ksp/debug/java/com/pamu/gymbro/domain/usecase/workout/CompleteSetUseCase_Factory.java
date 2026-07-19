package com.pamu.gymbro.domain.usecase.workout;

import com.pamu.gymbro.domain.repository.WorkoutSessionRepository;
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
public final class CompleteSetUseCase_Factory implements Factory<CompleteSetUseCase> {
  private final Provider<WorkoutSessionRepository> repositoryProvider;

  private CompleteSetUseCase_Factory(Provider<WorkoutSessionRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public CompleteSetUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static CompleteSetUseCase_Factory create(
      Provider<WorkoutSessionRepository> repositoryProvider) {
    return new CompleteSetUseCase_Factory(repositoryProvider);
  }

  public static CompleteSetUseCase newInstance(WorkoutSessionRepository repository) {
    return new CompleteSetUseCase(repository);
  }
}
