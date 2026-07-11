package com.pamu.gymbro.domain.usecase.dashboard;

import com.pamu.gymbro.domain.repository.DietRepository;
import com.pamu.gymbro.domain.repository.WorkoutRepository;
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
public final class GetDashboardSummaryUseCase_Factory implements Factory<GetDashboardSummaryUseCase> {
  private final Provider<WorkoutRepository> workoutRepositoryProvider;

  private final Provider<DietRepository> dietRepositoryProvider;

  private GetDashboardSummaryUseCase_Factory(Provider<WorkoutRepository> workoutRepositoryProvider,
      Provider<DietRepository> dietRepositoryProvider) {
    this.workoutRepositoryProvider = workoutRepositoryProvider;
    this.dietRepositoryProvider = dietRepositoryProvider;
  }

  @Override
  public GetDashboardSummaryUseCase get() {
    return newInstance(workoutRepositoryProvider.get(), dietRepositoryProvider.get());
  }

  public static GetDashboardSummaryUseCase_Factory create(
      Provider<WorkoutRepository> workoutRepositoryProvider,
      Provider<DietRepository> dietRepositoryProvider) {
    return new GetDashboardSummaryUseCase_Factory(workoutRepositoryProvider, dietRepositoryProvider);
  }

  public static GetDashboardSummaryUseCase newInstance(WorkoutRepository workoutRepository,
      DietRepository dietRepository) {
    return new GetDashboardSummaryUseCase(workoutRepository, dietRepository);
  }
}
