package com.pamu.gymbro.domain.usecase.dashboard;

import com.pamu.gymbro.domain.repository.DailyStatsRepository;
import com.pamu.gymbro.domain.repository.DietRepository;
import com.pamu.gymbro.domain.repository.WorkoutRepository;
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
public final class GetDashboardSummaryUseCase_Factory implements Factory<GetDashboardSummaryUseCase> {
  private final Provider<WorkoutRepository> workoutRepositoryProvider;

  private final Provider<DietRepository> dietRepositoryProvider;

  private final Provider<DailyStatsRepository> statsRepositoryProvider;

  private final Provider<WorkoutSessionRepository> sessionRepositoryProvider;

  private GetDashboardSummaryUseCase_Factory(Provider<WorkoutRepository> workoutRepositoryProvider,
      Provider<DietRepository> dietRepositoryProvider,
      Provider<DailyStatsRepository> statsRepositoryProvider,
      Provider<WorkoutSessionRepository> sessionRepositoryProvider) {
    this.workoutRepositoryProvider = workoutRepositoryProvider;
    this.dietRepositoryProvider = dietRepositoryProvider;
    this.statsRepositoryProvider = statsRepositoryProvider;
    this.sessionRepositoryProvider = sessionRepositoryProvider;
  }

  @Override
  public GetDashboardSummaryUseCase get() {
    return newInstance(workoutRepositoryProvider.get(), dietRepositoryProvider.get(), statsRepositoryProvider.get(), sessionRepositoryProvider.get());
  }

  public static GetDashboardSummaryUseCase_Factory create(
      Provider<WorkoutRepository> workoutRepositoryProvider,
      Provider<DietRepository> dietRepositoryProvider,
      Provider<DailyStatsRepository> statsRepositoryProvider,
      Provider<WorkoutSessionRepository> sessionRepositoryProvider) {
    return new GetDashboardSummaryUseCase_Factory(workoutRepositoryProvider, dietRepositoryProvider, statsRepositoryProvider, sessionRepositoryProvider);
  }

  public static GetDashboardSummaryUseCase newInstance(WorkoutRepository workoutRepository,
      DietRepository dietRepository, DailyStatsRepository statsRepository,
      WorkoutSessionRepository sessionRepository) {
    return new GetDashboardSummaryUseCase(workoutRepository, dietRepository, statsRepository, sessionRepository);
  }
}
