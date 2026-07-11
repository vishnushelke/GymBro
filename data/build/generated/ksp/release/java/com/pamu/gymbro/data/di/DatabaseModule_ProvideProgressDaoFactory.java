package com.pamu.gymbro.data.di;

import com.pamu.gymbro.data.local.GymBroDatabase;
import com.pamu.gymbro.data.local.dao.ProgressDao;
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
public final class DatabaseModule_ProvideProgressDaoFactory implements Factory<ProgressDao> {
  private final Provider<GymBroDatabase> dbProvider;

  private DatabaseModule_ProvideProgressDaoFactory(Provider<GymBroDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public ProgressDao get() {
    return provideProgressDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideProgressDaoFactory create(
      Provider<GymBroDatabase> dbProvider) {
    return new DatabaseModule_ProvideProgressDaoFactory(dbProvider);
  }

  public static ProgressDao provideProgressDao(GymBroDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideProgressDao(db));
  }
}
