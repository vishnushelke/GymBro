package com.pamu.gymbro.features.diet.presentation;

import com.pamu.gymbro.domain.usecase.diet.DeleteDietPlanUseCase;
import com.pamu.gymbro.domain.usecase.diet.GetDietPlansUseCase;
import com.pamu.gymbro.domain.usecase.diet.SaveDietPlanUseCase;
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
public final class DietListViewModel_Factory implements Factory<DietListViewModel> {
  private final Provider<GetDietPlansUseCase> getDietPlansUseCaseProvider;

  private final Provider<SaveDietPlanUseCase> saveDietPlanUseCaseProvider;

  private final Provider<DeleteDietPlanUseCase> deleteDietPlanUseCaseProvider;

  private final Provider<GetUserUseCase> getUserUseCaseProvider;

  private DietListViewModel_Factory(Provider<GetDietPlansUseCase> getDietPlansUseCaseProvider,
      Provider<SaveDietPlanUseCase> saveDietPlanUseCaseProvider,
      Provider<DeleteDietPlanUseCase> deleteDietPlanUseCaseProvider,
      Provider<GetUserUseCase> getUserUseCaseProvider) {
    this.getDietPlansUseCaseProvider = getDietPlansUseCaseProvider;
    this.saveDietPlanUseCaseProvider = saveDietPlanUseCaseProvider;
    this.deleteDietPlanUseCaseProvider = deleteDietPlanUseCaseProvider;
    this.getUserUseCaseProvider = getUserUseCaseProvider;
  }

  @Override
  public DietListViewModel get() {
    return newInstance(getDietPlansUseCaseProvider.get(), saveDietPlanUseCaseProvider.get(), deleteDietPlanUseCaseProvider.get(), getUserUseCaseProvider.get());
  }

  public static DietListViewModel_Factory create(
      Provider<GetDietPlansUseCase> getDietPlansUseCaseProvider,
      Provider<SaveDietPlanUseCase> saveDietPlanUseCaseProvider,
      Provider<DeleteDietPlanUseCase> deleteDietPlanUseCaseProvider,
      Provider<GetUserUseCase> getUserUseCaseProvider) {
    return new DietListViewModel_Factory(getDietPlansUseCaseProvider, saveDietPlanUseCaseProvider, deleteDietPlanUseCaseProvider, getUserUseCaseProvider);
  }

  public static DietListViewModel newInstance(GetDietPlansUseCase getDietPlansUseCase,
      SaveDietPlanUseCase saveDietPlanUseCase, DeleteDietPlanUseCase deleteDietPlanUseCase,
      GetUserUseCase getUserUseCase) {
    return new DietListViewModel(getDietPlansUseCase, saveDietPlanUseCase, deleteDietPlanUseCase, getUserUseCase);
  }
}
