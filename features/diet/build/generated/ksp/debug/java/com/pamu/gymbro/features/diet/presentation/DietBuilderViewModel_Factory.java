package com.pamu.gymbro.features.diet.presentation;

import com.pamu.gymbro.domain.usecase.diet.GetDietDetailsUseCase;
import com.pamu.gymbro.domain.usecase.diet.SaveDietPlanUseCase;
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
public final class DietBuilderViewModel_Factory implements Factory<DietBuilderViewModel> {
  private final Provider<SaveDietPlanUseCase> saveDietPlanUseCaseProvider;

  private final Provider<GetDietDetailsUseCase> getDietDetailsUseCaseProvider;

  private DietBuilderViewModel_Factory(Provider<SaveDietPlanUseCase> saveDietPlanUseCaseProvider,
      Provider<GetDietDetailsUseCase> getDietDetailsUseCaseProvider) {
    this.saveDietPlanUseCaseProvider = saveDietPlanUseCaseProvider;
    this.getDietDetailsUseCaseProvider = getDietDetailsUseCaseProvider;
  }

  @Override
  public DietBuilderViewModel get() {
    return newInstance(saveDietPlanUseCaseProvider.get(), getDietDetailsUseCaseProvider.get());
  }

  public static DietBuilderViewModel_Factory create(
      Provider<SaveDietPlanUseCase> saveDietPlanUseCaseProvider,
      Provider<GetDietDetailsUseCase> getDietDetailsUseCaseProvider) {
    return new DietBuilderViewModel_Factory(saveDietPlanUseCaseProvider, getDietDetailsUseCaseProvider);
  }

  public static DietBuilderViewModel newInstance(SaveDietPlanUseCase saveDietPlanUseCase,
      GetDietDetailsUseCase getDietDetailsUseCase) {
    return new DietBuilderViewModel(saveDietPlanUseCase, getDietDetailsUseCase);
  }
}
