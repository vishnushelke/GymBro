package com.pamu.gymbro.data.di;

import com.pamu.gymbro.data.local.GymBroDatabase;
import com.pamu.gymbro.data.local.dao.DailyStatsDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class DatabaseModule_ProvideDailyStatsDaoFactory implements Factory<DailyStatsDao> {
  private final Provider<GymBroDatabase> dbProvider;

  private DatabaseModule_ProvideDailyStatsDaoFactory(Provider<GymBroDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public DailyStatsDao get() {
    return provideDailyStatsDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideDailyStatsDaoFactory create(
      Provider<GymBroDatabase> dbProvider) {
    return new DatabaseModule_ProvideDailyStatsDaoFactory(dbProvider);
  }

  public static DailyStatsDao provideDailyStatsDao(GymBroDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideDailyStatsDao(db));
  }
}
