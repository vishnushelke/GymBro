package com.pamu.gymbro.features.exercise.presentation;

import com.pamu.gymbro.domain.usecase.exercise.GetCategoriesUseCase;
import com.pamu.gymbro.domain.usecase.exercise.GetExercisesUseCase;
import com.pamu.gymbro.domain.usecase.user.GetUserUseCase;
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
public final class ExerciseListViewModel_Factory implements Factory<ExerciseListViewModel> {
  private final Provider<GetExercisesUseCase> getExercisesUseCaseProvider;

  private final Provider<GetCategoriesUseCase> getCategoriesUseCaseProvider;

  private final Provider<GetUserUseCase> getUserUseCaseProvider;

  private ExerciseListViewModel_Factory(Provider<GetExercisesUseCase> getExercisesUseCaseProvider,
      Provider<GetCategoriesUseCase> getCategoriesUseCaseProvider,
      Provider<GetUserUseCase> getUserUseCaseProvider) {
    this.getExercisesUseCaseProvider = getExercisesUseCaseProvider;
    this.getCategoriesUseCaseProvider = getCategoriesUseCaseProvider;
    this.getUserUseCaseProvider = getUserUseCaseProvider;
  }

  @Override
  public ExerciseListViewModel get() {
    return newInstance(getExercisesUseCaseProvider.get(), getCategoriesUseCaseProvider.get(), getUserUseCaseProvider.get());
  }

  public static ExerciseListViewModel_Factory create(
      Provider<GetExercisesUseCase> getExercisesUseCaseProvider,
      Provider<GetCategoriesUseCase> getCategoriesUseCaseProvider,
      Provider<GetUserUseCase> getUserUseCaseProvider) {
    return new ExerciseListViewModel_Factory(getExercisesUseCaseProvider, getCategoriesUseCaseProvider, getUserUseCaseProvider);
  }

  public static ExerciseListViewModel newInstance(GetExercisesUseCase getExercisesUseCase,
      GetCategoriesUseCase getCategoriesUseCase, GetUserUseCase getUserUseCase) {
    return new ExerciseListViewModel(getExercisesUseCase, getCategoriesUseCase, getUserUseCase);
  }
}
