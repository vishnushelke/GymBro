package com.pamu.gymbro.features.home.presentation;

import com.pamu.gymbro.core.util.ConnectivityObserver;
import com.pamu.gymbro.domain.usecase.dashboard.GetDashboardSummaryUseCase;
import com.pamu.gymbro.domain.usecase.favorite.GetFavoriteItemsUseCase;
import com.pamu.gymbro.domain.usecase.stats.AddWaterUseCase;
import com.pamu.gymbro.domain.usecase.user.GetUserUseCase;
import com.pamu.gymbro.domain.usecase.workout.GetActiveWorkoutSessionUseCase;
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
public final class DashboardViewModel_Factory implements Factory<DashboardViewModel> {
  private final Provider<GetDashboardSummaryUseCase> getDashboardSummaryUseCaseProvider;

  private final Provider<GetFavoriteItemsUseCase> getFavoriteItemsUseCaseProvider;

  private final Provider<ConnectivityObserver> connectivityObserverProvider;

  private final Provider<GetUserUseCase> getUserUseCaseProvider;

  private final Provider<GetActiveWorkoutSessionUseCase> getActiveWorkoutSessionUseCaseProvider;

  private final Provider<AddWaterUseCase> addWaterUseCaseProvider;

  private DashboardViewModel_Factory(
      Provider<GetDashboardSummaryUseCase> getDashboardSummaryUseCaseProvider,
      Provider<GetFavoriteItemsUseCase> getFavoriteItemsUseCaseProvider,
      Provider<ConnectivityObserver> connectivityObserverProvider,
      Provider<GetUserUseCase> getUserUseCaseProvider,
      Provider<GetActiveWorkoutSessionUseCase> getActiveWorkoutSessionUseCaseProvider,
      Provider<AddWaterUseCase> addWaterUseCaseProvider) {
    this.getDashboardSummaryUseCaseProvider = getDashboardSummaryUseCaseProvider;
    this.getFavoriteItemsUseCaseProvider = getFavoriteItemsUseCaseProvider;
    this.connectivityObserverProvider = connectivityObserverProvider;
    this.getUserUseCaseProvider = getUserUseCaseProvider;
    this.getActiveWorkoutSessionUseCaseProvider = getActiveWorkoutSessionUseCaseProvider;
    this.addWaterUseCaseProvider = addWaterUseCaseProvider;
  }

  @Override
  public DashboardViewModel get() {
    return newInstance(getDashboardSummaryUseCaseProvider.get(), getFavoriteItemsUseCaseProvider.get(), connectivityObserverProvider.get(), getUserUseCaseProvider.get(), getActiveWorkoutSessionUseCaseProvider.get(), addWaterUseCaseProvider.get());
  }

  public static DashboardViewModel_Factory create(
      Provider<GetDashboardSummaryUseCase> getDashboardSummaryUseCaseProvider,
      Provider<GetFavoriteItemsUseCase> getFavoriteItemsUseCaseProvider,
      Provider<ConnectivityObserver> connectivityObserverProvider,
      Provider<GetUserUseCase> getUserUseCaseProvider,
      Provider<GetActiveWorkoutSessionUseCase> getActiveWorkoutSessionUseCaseProvider,
      Provider<AddWaterUseCase> addWaterUseCaseProvider) {
    return new DashboardViewModel_Factory(getDashboardSummaryUseCaseProvider, getFavoriteItemsUseCaseProvider, connectivityObserverProvider, getUserUseCaseProvider, getActiveWorkoutSessionUseCaseProvider, addWaterUseCaseProvider);
  }

  public static DashboardViewModel newInstance(
      GetDashboardSummaryUseCase getDashboardSummaryUseCase,
      GetFavoriteItemsUseCase getFavoriteItemsUseCase, ConnectivityObserver connectivityObserver,
      GetUserUseCase getUserUseCase, GetActiveWorkoutSessionUseCase getActiveWorkoutSessionUseCase,
      AddWaterUseCase addWaterUseCase) {
    return new DashboardViewModel(getDashboardSummaryUseCase, getFavoriteItemsUseCase, connectivityObserver, getUserUseCase, getActiveWorkoutSessionUseCase, addWaterUseCase);
  }
}
