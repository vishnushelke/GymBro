package com.pamu.gymbro.features.exercise.presentation;

import com.pamu.gymbro.domain.usecase.exercise.GetExerciseByIdUseCase;
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
public final class ExerciseDetailViewModel_Factory implements Factory<ExerciseDetailViewModel> {
  private final Provider<GetExerciseByIdUseCase> getExerciseByIdUseCaseProvider;

  private ExerciseDetailViewModel_Factory(
      Provider<GetExerciseByIdUseCase> getExerciseByIdUseCaseProvider) {
    this.getExerciseByIdUseCaseProvider = getExerciseByIdUseCaseProvider;
  }

  @Override
  public ExerciseDetailViewModel get() {
    return newInstance(getExerciseByIdUseCaseProvider.get());
  }

  public static ExerciseDetailViewModel_Factory create(
      Provider<GetExerciseByIdUseCase> getExerciseByIdUseCaseProvider) {
    return new ExerciseDetailViewModel_Factory(getExerciseByIdUseCaseProvider);
  }

  public static ExerciseDetailViewModel newInstance(GetExerciseByIdUseCase getExerciseByIdUseCase) {
    return new ExerciseDetailViewModel(getExerciseByIdUseCase);
  }
}
