package com.pamu.gymbro.features.diet.presentation;

import com.pamu.gymbro.domain.usecase.diet.GetDietDetailsUseCase;
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
public final class DietDetailViewModel_Factory implements Factory<DietDetailViewModel> {
  private final Provider<GetDietDetailsUseCase> getDietDetailsUseCaseProvider;

  private DietDetailViewModel_Factory(
      Provider<GetDietDetailsUseCase> getDietDetailsUseCaseProvider) {
    this.getDietDetailsUseCaseProvider = getDietDetailsUseCaseProvider;
  }

  @Override
  public DietDetailViewModel get() {
    return newInstance(getDietDetailsUseCaseProvider.get());
  }

  public static DietDetailViewModel_Factory create(
      Provider<GetDietDetailsUseCase> getDietDetailsUseCaseProvider) {
    return new DietDetailViewModel_Factory(getDietDetailsUseCaseProvider);
  }

  public static DietDetailViewModel newInstance(GetDietDetailsUseCase getDietDetailsUseCase) {
    return new DietDetailViewModel(getDietDetailsUseCase);
  }
}
