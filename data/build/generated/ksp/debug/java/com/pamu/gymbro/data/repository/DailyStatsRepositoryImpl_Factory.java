package com.pamu.gymbro.data.repository;

import com.pamu.gymbro.data.local.dao.DailyStatsDao;
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
public final class DailyStatsRepositoryImpl_Factory implements Factory<DailyStatsRepositoryImpl> {
  private final Provider<DailyStatsDao> daoProvider;

  private DailyStatsRepositoryImpl_Factory(Provider<DailyStatsDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public DailyStatsRepositoryImpl get() {
    return newInstance(daoProvider.get());
  }

  public static DailyStatsRepositoryImpl_Factory create(Provider<DailyStatsDao> daoProvider) {
    return new DailyStatsRepositoryImpl_Factory(daoProvider);
  }

  public static DailyStatsRepositoryImpl newInstance(DailyStatsDao dao) {
    return new DailyStatsRepositoryImpl(dao);
  }
}
