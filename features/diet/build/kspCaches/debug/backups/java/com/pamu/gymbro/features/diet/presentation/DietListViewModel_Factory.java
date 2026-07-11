package com.pamu.gymbro.features.diet.presentation;

import com.pamu.gymbro.domain.usecase.diet.GetDietPlansUseCase;
import com.pamu.gymbro.domain.usecase.favorite.ToggleFavoriteUseCase;
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
public final class DietListViewModel_Factory implements Factory<DietListViewModel> {
  private final Provider<GetDietPlansUseCase> getDietPlansUseCaseProvider;

  private final Provider<ToggleFavoriteUseCase> toggleFavoriteUseCaseProvider;

  private DietListViewModel_Factory(Provider<GetDietPlansUseCase> getDietPlansUseCaseProvider,
      Provider<ToggleFavoriteUseCase> toggleFavoriteUseCaseProvider) {
    this.getDietPlansUseCaseProvider = getDietPlansUseCaseProvider;
    this.toggleFavoriteUseCaseProvider = toggleFavoriteUseCaseProvider;
  }

  @Override
  public DietListViewModel get() {
    return newInstance(getDietPlansUseCaseProvider.get(), toggleFavoriteUseCaseProvider.get());
  }

  public static DietListViewModel_Factory create(
      Provider<GetDietPlansUseCase> getDietPlansUseCaseProvider,
      Provider<ToggleFavoriteUseCase> toggleFavoriteUseCaseProvider) {
    return new DietListViewModel_Factory(getDietPlansUseCaseProvider, toggleFavoriteUseCaseProvider);
  }

  public static DietListViewModel newInstance(GetDietPlansUseCase getDietPlansUseCase,
      ToggleFavoriteUseCase toggleFavoriteUseCase) {
    return new DietListViewModel(getDietPlansUseCase, toggleFavoriteUseCase);
  }
}
