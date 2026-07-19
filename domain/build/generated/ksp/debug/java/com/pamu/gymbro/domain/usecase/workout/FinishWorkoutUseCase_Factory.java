package com.pamu.gymbro.domain.usecase.workout;

import com.pamu.gymbro.domain.repository.DailyStatsRepository;
import com.pamu.gymbro.domain.repository.ProgressRepository;
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
public final class FinishWorkoutUseCase_Factory implements Factory<FinishWorkoutUseCase> {
  private final Provider<WorkoutSessionRepository> sessionRepositoryProvider;

  private final Provider<DailyStatsRepository> statsRepositoryProvider;

  private final Provider<ProgressRepository> progressRepositoryProvider;

  private FinishWorkoutUseCase_Factory(Provider<WorkoutSessionRepository> sessionRepositoryProvider,
      Provider<DailyStatsRepository> statsRepositoryProvider,
      Provider<ProgressRepository> progressRepositoryProvider) {
    this.sessionRepositoryProvider = sessionRepositoryProvider;
    this.statsRepositoryProvider = statsRepositoryProvider;
    this.progressRepositoryProvider = progressRepositoryProvider;
  }

  @Override
  public FinishWorkoutUseCase get() {
    return newInstance(sessionRepositoryProvider.get(), statsRepositoryProvider.get(), progressRepositoryProvider.get());
  }

  public static FinishWorkoutUseCase_Factory create(
      Provider<WorkoutSessionRepository> sessionRepositoryProvider,
      Provider<DailyStatsRepository> statsRepositoryProvider,
      Provider<ProgressRepository> progressRepositoryProvider) {
    return new FinishWorkoutUseCase_Factory(sessionRepositoryProvider, statsRepositoryProvider, progressRepositoryProvider);
  }

  public static FinishWorkoutUseCase newInstance(WorkoutSessionRepository sessionRepository,
      DailyStatsRepository statsRepository, ProgressRepository progressRepository) {
    return new FinishWorkoutUseCase(sessionRepository, statsRepository, progressRepository);
  }
}
