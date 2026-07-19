package com.pamu.gymbro.domain.usecase.stats;

import com.pamu.gymbro.domain.repository.DailyStatsRepository;
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
public final class AddWaterUseCase_Factory implements Factory<AddWaterUseCase> {
  private final Provider<DailyStatsRepository> repositoryProvider;

  private AddWaterUseCase_Factory(Provider<DailyStatsRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public AddWaterUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static AddWaterUseCase_Factory create(Provider<DailyStatsRepository> repositoryProvider) {
    return new AddWaterUseCase_Factory(repositoryProvider);
  }

  public static AddWaterUseCase newInstance(DailyStatsRepository repository) {
    return new AddWaterUseCase(repository);
  }
}
